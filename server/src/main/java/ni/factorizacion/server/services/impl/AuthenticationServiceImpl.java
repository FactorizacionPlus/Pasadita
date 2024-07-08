package ni.factorizacion.server.services.impl;

import jakarta.transaction.Transactional;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Terminal;
import ni.factorizacion.server.domain.entities.Token;
import ni.factorizacion.server.domain.entities.TokenType;
import ni.factorizacion.server.repositories.RegisteredUserRepository;
import ni.factorizacion.server.repositories.TokenRepository;
import ni.factorizacion.server.services.AuthenticationService;
import ni.factorizacion.server.services.TerminalService;
import ni.factorizacion.server.services.TokenService;
import ni.factorizacion.server.types.ControlException;
import ni.factorizacion.server.types.GoogleAccessToken;
import ni.factorizacion.server.types.GoogleUserInfo;
import ni.factorizacion.server.utils.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${google.client_id}")
    private String googleClientId;
    @Value("${google.client_secret}")
    private String googleClientSecret;
    @Value("${google.endpoint_token}")
    private String googleEndpointToken;
    @Value("${google.endpoint_userinfo}")
    private String googleEndpointUserInfo;

    @Autowired
    private JWTTools jwtTools;
    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private RegisteredUserRepository registeredUserRepository;

    @Autowired
    private TerminalService terminalService;

    @Override
    public String getGoogleToken(String code, String redirectUri) throws ControlException {
        // TODO: Validate errors

        WebClient client = WebClient.builder()
                .baseUrl(googleEndpointToken)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();

        ResponseSpec response = client.post().body(BodyInserters.
                fromFormData("client_id", googleClientId)
                .with("client_secret", googleClientSecret)
                .with("code", code)
                .with("grant_type", "authorization_code")
                .with("redirect_uri", redirectUri)
        ).retrieve();

        GoogleAccessToken googleToken = response.bodyToFlux(GoogleAccessToken.class).blockLast();

        if (googleToken == null) {
            throw new ControlException(HttpStatus.INTERNAL_SERVER_ERROR, "Could not retrieve Google Access Token");
        }
        return googleToken.getAccess_token();
    }

    @Override
    @Transactional(rollbackOn = ControlException.class)
    public Token registerToken(RegisteredUser user) {
        tokenService.cleanTokens(user);

        String tokenString = jwtTools.generateToken(user.getEmail());
        Token token = new Token(tokenString, user, TokenType.AUTH);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public Boolean isTokenValid(RegisteredUser user, String token) {
        try {
            tokenService.cleanTokens(user);
            List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

            tokens.stream()
                    .filter(tk -> tk.getContent().equals(token))
                    .findAny()
                    .orElseThrow(Exception::new);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<RegisteredUser> getCurrentAuthenticatedUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();

        if (principal instanceof RegisteredUser user) {
            return registeredUserRepository.findByEmail(user.getEmail());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Terminal> getCurrentAuthenticatedTerminal() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();

        if (principal instanceof Terminal terminal) {
            return terminalService.findTerminal(terminal.getType());
        }
        return Optional.empty();
    }

    @Override
    public Optional<GoogleUserInfo> getUserInfoFromToken(String token) {
        WebClient client = WebClient.builder()
                .baseUrl(googleEndpointUserInfo)
                .build();

        ResponseSpec response = client.get()
                .headers(h -> h.setBearerAuth(token))
                .retrieve();
        GoogleUserInfo userInfo = response.bodyToFlux(GoogleUserInfo.class).blockLast();
        return Optional.ofNullable(userInfo);
    }
}
