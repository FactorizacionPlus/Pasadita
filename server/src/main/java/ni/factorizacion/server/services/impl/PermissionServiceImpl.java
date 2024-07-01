package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.dtos.input.SavePermissionDto;
import ni.factorizacion.server.domain.entities.*;
import ni.factorizacion.server.repositories.PermissionRepository;
import ni.factorizacion.server.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Optional<Permission> findById(UUID uuid) {
        return permissionRepository.findById(uuid);
    }

    @Override
    public List<Permission> findAllByResidence(Residence residence) {
        return permissionRepository.findAllByResidence(residence);
    }

    @Override
    public List<Permission> findAllByResident(Resident resident) {
        return permissionRepository.findAllByResident(resident);
    }

    @Override
    public List<Permission> findAllByInvitedUser(InvitedUser user) {
        return permissionRepository.findAllByInvitedUser(user);
    }

    @Override
    public Optional<Permission> findByUserNow(InvitedUser user) {
        return permissionRepository.findByUserAndDate(user, LocalDateTime.now());
    }

    @Override
    public Optional<Permission> createFrom(SavePermissionDto dto, Resident resident, InvitedUser user) {
        Permission permission = new Permission();

        permission.setCreationDate(LocalDateTime.now());
        permission.setStartDate(dto.getStartDate());
        permission.setEndDate(dto.getEndDate());

        permission.setResidence(resident.getResidence());
        permission.setResident(resident);
        permission.setInvitedUser(user);

        // Si es un Residente Encargado, se autoriza automáticamente
        if (resident.getRole() == ResidentRole.SUDO) {
            permission.setAuthorized(true);
        } else {
            permission.setAuthorized(null);
        }

        return Optional.of(permission);
    }

    @Override
    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }
}
