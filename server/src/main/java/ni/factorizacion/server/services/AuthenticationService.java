package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Token;
import ni.factorizacion.server.types.ControlException;
import ni.factorizacion.server.types.GoogleUserInfo;

import java.util.Optional;

public interface AuthenticationService {

    String getGithubToken(String code);

    String getGoogleToken(String code) throws ControlException;

    Optional<GoogleUserInfo> getUserInfoFromToken(String token);

    Token registerToken(RegisteredUser user) throws ControlException;

    Boolean isTokenValid(RegisteredUser user, String token);

    void cleanTokens(RegisteredUser user) throws ControlException;

//    public static Authentication getAuthentication(HttpServletRequest request) {
//        String apiKey = request.getHeader(AUTH_TOKEN_HEADER_NAME);
//        if (apiKey == null || !apiKey.equals(AUTH_TOKEN)) {
//            throw new BadCredentialsException("Invalid API Key");
//        }
//
//        return new ApiKeyAuthentication(apiKey, AuthorityUtils.NO_AUTHORITIES);
//    }
}
