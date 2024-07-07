package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.domain.dtos.input.SavePermissionDto;
import ni.factorizacion.server.domain.entities.*;
import ni.factorizacion.server.repositories.PermissionRepository;
import ni.factorizacion.server.services.ConfigurationService;
import ni.factorizacion.server.services.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    PermissionRepository permissionRepository;

    @Autowired
    ConfigurationService configurationService;

    @Override
    public Page<Permission> findAll(Pageable pageable) {
        return permissionRepository.findAll(pageable);
    }

    @Override
    public Optional<Permission> findById(UUID uuid) {
        return permissionRepository.findById(uuid);
    }

    @Override
    public Page<Permission> findAllByResidence(Residence residence, Pageable pageable) {
        return permissionRepository.findAllByResidence(residence, pageable);
    }

    @Override
    public Page<Permission> findAllByResident(Resident resident, Pageable pageable) {
        return permissionRepository.findAllByResident(resident, pageable);
    }

    @Override
    public Page<Permission> findAllByInvitedUser(InvitedUser user, Pageable pageable) {
        return permissionRepository.findAllByInvitedUser(user, pageable);
    }

    @Override
    public Optional<Permission> findByUserNow(InvitedUser user) {
        Long timeframe = configurationService.getConfiguration().getTimeframe();
        return permissionRepository.findByUserAndDate(user, LocalDateTime.now(), timeframe);
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
