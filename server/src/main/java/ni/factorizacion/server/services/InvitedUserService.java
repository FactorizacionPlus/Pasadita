package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.types.GoogleUserInfo;

import java.util.Optional;

public interface InvitedUserService {
    Optional<InvitedUser> findByIdentifier(String identifier);

    Optional<InvitedUser> findByEmail(String email);

    InvitedUser createUser(GoogleUserInfo userInfo);

    Optional<InvitedUser> createFrom(RegisteredUser user);

    void save(InvitedUser invitedUser);
}
