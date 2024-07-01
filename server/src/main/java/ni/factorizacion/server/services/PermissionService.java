package ni.factorizacion.server.services;

import ni.factorizacion.server.domain.dtos.input.SavePermissionDto;
import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.Permission;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.Resident;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PermissionService {
    List<Permission> findAll();

    Optional<Permission> findById(UUID uuid);

    List<Permission> findAllByResidence(Residence residence);

    List<Permission> findAllByResident(Resident resident);

    List<Permission> findAllByInvitedUser(InvitedUser user);

    Optional<Permission> findByUserNow(InvitedUser user);

    Optional<Permission> createFrom(SavePermissionDto dto, Resident resident, InvitedUser user);

    Permission save(Permission permission);
}
