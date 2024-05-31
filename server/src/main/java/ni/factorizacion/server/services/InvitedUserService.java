package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.types.ControlException;
import ni.factorizacion.server.types.GoogleUserInfo;

import java.util.Optional;

public interface InvitedUserService {
    Optional<InvitedUser> findByEmail(String email);

    InvitedUser createUser(GoogleUserInfo userInfo) throws ControlException;
}