package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.input.AuthorizePermissionDto;
import ni.factorizacion.server.domain.dtos.input.EditPermissionDto;
import ni.factorizacion.server.domain.dtos.input.SavePermissionDto;
import ni.factorizacion.server.domain.dtos.output.PermissionSimpleDto;
import ni.factorizacion.server.domain.entities.*;
import ni.factorizacion.server.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/permission")
public class PermissionRestController {

    @Autowired
    PermissionService permissionService;

    @Autowired
    ResidenceService residenceService;

    @Autowired
    RegisteredUserService registeredUserService;

    @Autowired
    InvitedUserService invitedUserService;

    @Autowired
    AuthenticationService authenticationService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<Page<PermissionSimpleDto>>> getPermissions(Pageable pageable) {
        Page<Permission> permissions = permissionService.findAll(pageable);
        Page<PermissionSimpleDto> permissionSimpleDtos = permissions.map(PermissionSimpleDto::from);

        return GeneralResponse.ok("Permissions found", permissionSimpleDtos);
    }

    @GetMapping("/own")
    @PreAuthorize("hasRole('ROLE_INVITED') or hasRole('ROLE_RESIDENT')")
    public ResponseEntity<GeneralResponse<Page<PermissionSimpleDto>>> getOwnPermissions(Pageable pageable) {
        Optional<RegisteredUser> registeredUser = authenticationService.getCurrentAuthenticatedUser();
        if (registeredUser.isEmpty()) {
            return GeneralResponse.error401("Registered user not found");
        }
        Page<Permission> permissions;
        if (registeredUser.get().getClass().equals(InvitedUser.class)) {
            InvitedUser invitedUser = (InvitedUser) registeredUser.get();
            permissions = permissionService.findAllByInvitedUser(invitedUser, pageable);
        } else {
            Resident resident = (Resident) registeredUser.get();
            permissions = permissionService.findAllByResident(resident, pageable);
        }
        Page<PermissionSimpleDto> permissionSimpleDtos = permissions.map(PermissionSimpleDto::from);

        return GeneralResponse.ok("Permissions found", permissionSimpleDtos);
    }

    @GetMapping("/own-active")
    @PreAuthorize("hasRole('ROLE_INVITED')")
    public ResponseEntity<GeneralResponse<PermissionSimpleDto>> getOwnActivePermission() {
        Optional<RegisteredUser> registeredUser = authenticationService.getCurrentAuthenticatedUser();
        if (registeredUser.isEmpty()) {
            return GeneralResponse.error401("Registered user not found");
        }

        InvitedUser invitedUser = (InvitedUser) registeredUser.get();
        Optional<Permission> permission = permissionService.findByUserNow(invitedUser);
        if (permission.isEmpty()) {
            return GeneralResponse.error404("Permission not found");
        }

        PermissionSimpleDto permissionDto = PermissionSimpleDto.from(permission.get());
        return GeneralResponse.ok("Permission found", permissionDto);
    }

    @GetMapping("/own-residence")
    @PreAuthorize("hasRole('ROLE_RESIDENT_SUDO')")
    public ResponseEntity<GeneralResponse<Page<PermissionSimpleDto>>> getOwnResidencePermissions(Pageable pageable) {
        Optional<RegisteredUser> registeredUser = authenticationService.getCurrentAuthenticatedUser();
        if (registeredUser.isEmpty()) {
            return GeneralResponse.error401("Registered user not found");
        }
        Resident resident = (Resident) registeredUser.get();
        Page<Permission> permissions = permissionService.findAllByResident(resident, pageable);
        Page<PermissionSimpleDto> permissionSimpleDtos = permissions.map(PermissionSimpleDto::from);

        return GeneralResponse.ok("Permissions found", permissionSimpleDtos);
    }

    @GetMapping(value = "/residence/{uuid}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<Page<PermissionSimpleDto>>> getResidencePermissions(@PathVariable UUID uuid, Pageable pageable) {
        Optional<Residence> residenceOptional = residenceService.findById(uuid);
        if (residenceOptional.isEmpty()) {
            return GeneralResponse.error404("Residence not found");
        }

        Page<Permission> permissions = permissionService.findAllByResidence(residenceOptional.get(), pageable);
        Page<PermissionSimpleDto> permissionSimpleDtos = permissions.map(PermissionSimpleDto::from);

        return GeneralResponse.ok("Permissions found", permissionSimpleDtos);
    }

    @GetMapping("/invited/{identifier}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<Page<PermissionSimpleDto>>> getUserPermissions(@PathVariable String identifier, Pageable pageable) {
        Optional<InvitedUser> invitedUser = invitedUserService.findByIdentifier(identifier);
        if (invitedUser.isEmpty()) {
            return GeneralResponse.error404("Invited User not found");
        }

        Page<Permission> permissions = permissionService.findAllByInvitedUser(invitedUser.get(), pageable);
        Page<PermissionSimpleDto> permissionSimpleDtos = permissions.map(PermissionSimpleDto::from);

        return GeneralResponse.ok("Permissions found", permissionSimpleDtos);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_RESIDENT')")
    public ResponseEntity<GeneralResponse<String>> createPermission(@RequestBody SavePermissionDto dto) {
        Optional<RegisteredUser> registeredUser = authenticationService.getCurrentAuthenticatedUser();
        if (registeredUser.isEmpty()) {
            return GeneralResponse.error401("Registered user not found");
        }
        Resident resident = (Resident) registeredUser.get();
        if (resident.getResidence() == null) {
            return GeneralResponse.error404("Resident does not have a Residence");
        }

        Optional<InvitedUser> invitedUser = invitedUserService.findByIdentifier(dto.getInvitedUser());
        if (invitedUser.isEmpty()) {
            return GeneralResponse.error404("Invited user not found");
        }

        Optional<Permission> permission = permissionService.createFrom(dto, resident, invitedUser.get());
        if (permission.isEmpty()) {
            return GeneralResponse.error500("Could not create Permission");
        }
        permissionService.save(permission.get());

        return GeneralResponse.ok("Permission created", null);
    }

    @PutMapping("/{uuid}")
    @PreAuthorize("hasRole('ROLE_RESIDENT')")
    public ResponseEntity<GeneralResponse<String>> modifyPermission(@PathVariable UUID uuid, @RequestBody EditPermissionDto dto) {
        Optional<RegisteredUser> registeredUser = authenticationService.getCurrentAuthenticatedUser();
        if (registeredUser.isEmpty()) {
            return GeneralResponse.error401("Registered user not found");
        }
        Resident resident = (Resident) registeredUser.get();
        if (resident.getResidence() == null) {
            return GeneralResponse.error404("Resident does not have a Residence");
        }

        Optional<Permission> permission = permissionService.findById(uuid);
        if (permission.isEmpty()) {
            return GeneralResponse.error404("Permission not found");
        }
        Permission permissionToModify = permission.get();
        // Si el Residente es NORMAL y el permiso ya fue autorizado/denegado, no se podrá modificar
        if (resident.getRole() == ResidentRole.NORMAL && permissionToModify.getAuthorized() != null) {
            return GeneralResponse.error409("Permission cannot be modified");
        }

        permissionToModify.setStartDate(dto.getStartDate());
        permissionToModify.setEndDate(dto.getEndDate());

        permissionService.save(permission.get());

        return GeneralResponse.ok("Permission modified", null);
    }

    @DeleteMapping("/{uuid}")
    @PreAuthorize("hasRole('ROLE_RESIDENT')")
    public ResponseEntity<GeneralResponse<String>> deletePermission(@PathVariable UUID uuid) {
        Optional<RegisteredUser> registeredUser = authenticationService.getCurrentAuthenticatedUser();
        if (registeredUser.isEmpty()) {
            return GeneralResponse.error401("Registered user not found");
        }
        Resident resident = (Resident) registeredUser.get();
        if (resident.getResidence() == null) {
            return GeneralResponse.error404("Resident does not have a Residence");
        }

        Optional<Permission> permission = permissionService.findById(uuid);
        if (permission.isEmpty()) {
            return GeneralResponse.error404("Permission not found");
        }
        Permission permissionToDelete = permission.get();

        // Si el Residente es NORMAL y el permiso ya fue autorizado/denegado, no se podrá eliminar
        if (resident.getRole() == ResidentRole.NORMAL && permissionToDelete.getAuthorized() != null) {
            return GeneralResponse.error409("Permission cannot be deleted");
        }

        permissionService.delete(permissionToDelete);

        return GeneralResponse.ok("Permission removed", null);
    }

    @PostMapping("/authorize")
    @PreAuthorize("hasRole('ROLE_RESIDENT_SUDO')")
    public ResponseEntity<GeneralResponse<String>> authorizePermission(@RequestBody AuthorizePermissionDto dto) {
        Optional<Permission> permission = permissionService.findById(dto.getPermission());
        if (permission.isEmpty()) {
            return GeneralResponse.error404("Permission not found");
        }

        permission.get().setAuthorized(dto.isAuthorized());
        permissionService.save(permission.get());

        return GeneralResponse.ok("Permission authorized", null);
    }
}
