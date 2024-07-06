package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.RegisteredUser;

public interface TokenService {
    void cleanTokens(RegisteredUser user);

    void invalidateQrTokensByUser(RegisteredUser user);
}
