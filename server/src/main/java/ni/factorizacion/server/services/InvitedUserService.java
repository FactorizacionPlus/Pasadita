package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.InvitedUser;

import java.util.Optional;

public interface InvitedUserService {
    Optional<InvitedUser> findByEmail(String email);
}
