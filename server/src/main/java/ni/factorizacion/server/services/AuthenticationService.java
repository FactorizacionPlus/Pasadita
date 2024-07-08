package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Terminal;
import ni.factorizacion.server.domain.entities.Token;
import ni.factorizacion.server.types.ControlException;
import ni.factorizacion.server.types.GoogleUserInfo;

import java.util.Optional;

public interface AuthenticationService {

    String getGoogleToken(String code, String redirectUri) throws ControlException;

    Optional<GoogleUserInfo> getUserInfoFromToken(String token);

    Token registerToken(RegisteredUser user);

    Boolean isTokenValid(RegisteredUser user, String token);

    Optional<RegisteredUser> getCurrentAuthenticatedUser();

    Optional<Terminal> getCurrentAuthenticatedTerminal();
}
