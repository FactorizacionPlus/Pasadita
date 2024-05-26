package ni.factorizacion.server.services.impl;

import jakarta.transaction.Transactional;
import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.Token;
import ni.factorizacion.server.repositories.TokenRepository;
import ni.factorizacion.server.services.AuthenticationService;
import ni.factorizacion.server.types.ControlException;
import ni.factorizacion.server.types.GithubAccessToken;
import ni.factorizacion.server.types.GoogleAccessToken;
import ni.factorizacion.server.types.GoogleUserInfo;
import ni.factorizacion.server.utils.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClient.ResponseSpec;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Value("${github.client_id}")
    private String githubClientId;
    @Value("${github.client_secret}")
    private String githubClientSecret;
    @Value("${github.endpoint_token}")
    private String githubEndpointToken;

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

    @Override
    public String getGithubToken(String code) {
        WebClient client = WebClient.builder()
                .baseUrl(githubEndpointToken)
                .defaultUriVariables(Map.ofEntries(
                        new AbstractMap.SimpleEntry<>("client_id", githubClientId),
                        new AbstractMap.SimpleEntry<>("client_secret", githubClientSecret),
                        new AbstractMap.SimpleEntry<>("code", code))
                ).build();

        ResponseSpec response = client.post().retrieve();
        GithubAccessToken token = response.bodyToFlux(GithubAccessToken.class).blockLast();

        return token.getAccess_token();
    }

    @Override
    public String getGoogleToken(String code) throws ControlException {
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
                .with("redirect_uri", "http://localhost:8080/auth/login/google")
        ).retrieve();

        GoogleAccessToken googleToken = response.bodyToFlux(GoogleAccessToken.class).blockLast();

        return googleToken.getAccess_token();
    }

    @Override
    @Transactional(rollbackOn = ControlException.class)
    public Token registerToken(InvitedUser user) throws ControlException {
        cleanTokens(user);

        String tokenString = jwtTools.generateToken(user);
        Token token = new Token(tokenString, user);

        tokenRepository.save(token);

        return token;
    }

    @Override
    public Boolean isTokenValid(InvitedUser user, String token) {
        try {
            cleanTokens(user);
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
    @Transactional(rollbackOn = ControlException.class)
    public void cleanTokens(InvitedUser user) throws ControlException {
        List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

        tokens.forEach(token -> {
            if (!jwtTools.verifyToken(token.getContent())) {
                token.setActive(false);
                tokenRepository.save(token);
            }
        });
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
