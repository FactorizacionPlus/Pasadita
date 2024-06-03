package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.Permission;

import java.util.Optional;

public interface PermissionService {
    Optional<Permission> findByUserNow(InvitedUser user);
}
