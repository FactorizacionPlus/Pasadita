package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.input.AuthorizePermissionDto;
import ni.factorizacion.server.domain.dtos.input.SavePermissionDto;
import ni.factorizacion.server.domain.entities.*;
import ni.factorizacion.server.services.*;
import ni.factorizacion.server.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
    public ResponseEntity<GeneralResponse<List<Permission>>> getPermissions() {
        List<Permission> permissions = permissionService.findAll();
        return GeneralResponse.ok("Permissions found", permissions);
    }

    @GetMapping("/own")
    @PreAuthorize("hasRole('ROLE_INVITED') or hasRole('ROLE_RESIDENT')")
    public ResponseEntity<GeneralResponse<List<Permission>>> getOwnPermissions() {
        Optional<RegisteredUser> registeredUser = authenticationService.getCurrentAuthenticatedUser();
        if (registeredUser.isEmpty()) {
            return GeneralResponse.error401("Registered user not found");
        }
        List<Permission> permissions;
        if (registeredUser.get().getClass().equals(InvitedUser.class)) {
            InvitedUser invitedUser = (InvitedUser) registeredUser.get();
            permissions = permissionService.findAllByInvitedUser(invitedUser);
        } else {
            Resident resident = (Resident) registeredUser.get();
            permissions = permissionService.findAllByResident(resident);
        }

        return GeneralResponse.ok("Permissions found", permissions);
    }

    @GetMapping("/own-residence")
    @PreAuthorize("hasRole('ROLE_RESIDENT_SUDO')")
    public ResponseEntity<GeneralResponse<List<Permission>>> getOwnResidencePermissions() {
        Optional<RegisteredUser> registeredUser = authenticationService.getCurrentAuthenticatedUser();
        if (registeredUser.isEmpty()) {
            return GeneralResponse.error401("Registered user not found");
        }
        Resident resident = (Resident) registeredUser.get();
        List<Permission> permissions = permissionService.findAllByResident(resident);

        return GeneralResponse.ok("Permissions found", permissions);
    }

    @GetMapping(value = "/residence/{residence}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<List<Permission>>> getResidencePermissions(@PathVariable String residence) {
        Optional<UUID> uuid = UUIDUtils.fromString(residence);
        if (uuid.isEmpty()) {
            return GeneralResponse.error400("Incorrect UUID");
        }

        Optional<Residence> residenceOptional = residenceService.findById(uuid.get());
        if (residenceOptional.isEmpty()) {
            return GeneralResponse.error404("Residence not found");
        }

        List<Permission> permissions = permissionService.findAllByResidence(residenceOptional.get());

        return GeneralResponse.ok("Permissions found", permissions);
    }

    @GetMapping("/invited/{identifier}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<List<Permission>>> getUserPermissions(@PathVariable String identifier) {
        Optional<InvitedUser> invitedUser = invitedUserService.findByIdentifier(identifier);
        if (invitedUser.isEmpty()) {
            return GeneralResponse.error404("Invited User not found");
        }

        List<Permission> permissions = permissionService.findAllByInvitedUser(invitedUser.get());

        return GeneralResponse.ok("Permissions found", permissions);
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