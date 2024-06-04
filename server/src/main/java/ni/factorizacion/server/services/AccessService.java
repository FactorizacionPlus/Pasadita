package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Token;
import ni.factorizacion.server.types.ControlException;

public interface AccessService {
    Token getQrToken(RegisteredUser user);
    void cleanTokens(RegisteredUser user) throws ControlException;
}
