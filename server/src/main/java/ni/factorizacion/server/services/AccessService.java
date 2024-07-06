package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Token;
import org.javatuples.Triplet;

import java.util.Optional;
import java.util.UUID;

public interface AccessService {
    Token getQrToken(RegisteredUser user);

    boolean validateQrToken(Token token);

    Optional<Token> findQrToken(UUID id);
}
