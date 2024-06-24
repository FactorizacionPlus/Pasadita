package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Token;
import ni.factorizacion.server.types.ControlException;

import java.util.Optional;

public interface AccessService {
    Token getQrToken(RegisteredUser user);

    boolean validateQrToken(Token token);

    Optional<Token> findQrToken(String content);

    void cleanTokens(RegisteredUser user) throws ControlException;
}
