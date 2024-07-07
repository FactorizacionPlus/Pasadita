package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.dtos.input.SavePermissionDto;
import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.Permission;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.Resident;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface PermissionService {
    Page<Permission> findAll(Pageable pageable);

    Optional<Permission> findById(UUID uuid);

    Page<Permission> findAllByResidence(Residence residence, Pageable pageable);

    Page<Permission> findAllByResident(Resident resident, Pageable pageable);

    Page<Permission> findAllByInvitedUser(InvitedUser user, Pageable pageable);

    Optional<Permission> findByUserNow(InvitedUser user);

    Optional<Permission> createFrom(SavePermissionDto dto, Resident resident, InvitedUser user);

    Permission save(Permission permission);
}
