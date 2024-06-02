package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.Permission;
import ni.factorizacion.server.repositories.PermissionRepository;
import ni.factorizacion.server.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public Optional<Permission> findByUserNow(InvitedUser user) {
        var permission = permissionRepository.findByUserAndDate(user, LocalDateTime.now());
        return Optional.ofNullable(permission);
    }
}
