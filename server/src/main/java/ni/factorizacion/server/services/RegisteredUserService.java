package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.RegisteredUser;

import java.util.Optional;

public interface RegisteredUserService {
    Optional<RegisteredUser> findByEmail(String email);
}
