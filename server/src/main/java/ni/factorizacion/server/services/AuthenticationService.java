package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Token;
import ni.factorizacion.server.types.ControlException;
import ni.factorizacion.server.types.GoogleUserInfo;

import java.util.Optional;

public interface AuthenticationService {

    String getGithubToken(String code) throws ControlException;

    String getGoogleToken(String code) throws ControlException;

    Optional<GoogleUserInfo> getUserInfoFromToken(String token);

    Token registerToken(RegisteredUser user) throws ControlException;

    Boolean isTokenValid(RegisteredUser user, String token);

    void cleanTokens(RegisteredUser user) throws ControlException;
}
