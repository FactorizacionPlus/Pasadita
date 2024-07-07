package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.input.FinishRegisterDto;
import ni.factorizacion.server.domain.dtos.input.SetUserStatusDto;
import ni.factorizacion.server.domain.dtos.output.RegisteredUserSimpleDto;
import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Resident;
import ni.factorizacion.server.services.AuthenticationService;
import ni.factorizacion.server.services.InvitedUserService;
import ni.factorizacion.server.services.RegisteredUserService;
import ni.factorizacion.server.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/registered")
public class RegisteredUserRestController {
    @Autowired
    RegisteredUserService registeredUserService;

    @Autowired
    InvitedUserService invitedUserService;

    @Autowired
    ResidentService residentService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping("/status")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<String>> setUserStatus(@RequestBody SetUserStatusDto dto) {
        Optional<RegisteredUser> currentUser = authenticationService.getCurrentAuthenticatedUser();
        if (currentUser.isPresent() && currentUser.get().getIdentifier().equals(dto.getIdentifier())) {
            return GeneralResponse.error418("Cannot change current user status");
        }

        Optional<RegisteredUser> registeredUser = registeredUserService.findByIdentifier(dto.getIdentifier());
        if (registeredUser.isEmpty()) {
            return GeneralResponse.error404("User not found");
        }
        RegisteredUser user = registeredUser.get();

        user.setStatus(dto.getStatus());
        registeredUserService.save(user);

        return GeneralResponse.ok("Set status", null);
    }

    @PostMapping("/migrate/invited/{identifier}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<String>> migrateUserToInvited(@PathVariable String identifier) {
        Optional<RegisteredUser> registeredUser = registeredUserService.findByIdentifier(identifier);
        if (registeredUser.isEmpty()) {
            return GeneralResponse.error404("User not found");
        }

        Optional<InvitedUser> invitedUser = invitedUserService.createFrom(registeredUser.get());
        if (invitedUser.isEmpty()) {
            return GeneralResponse.error418("Could not create invited user");
        }

        registeredUserService.delete(registeredUser.get());
        invitedUserService.save(invitedUser.get());

        return GeneralResponse.ok("Migrated to Invited", null);
    }

    @PostMapping("/migrate/resident/{identifier}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<String>> migrateUserToResident(@PathVariable String identifier) {
        Optional<RegisteredUser> registeredUser = registeredUserService.findByIdentifier(identifier);
        if (registeredUser.isEmpty()) {
            return GeneralResponse.error404("User not found");
        }

        Optional<Resident> resident = residentService.createFrom(registeredUser.get());
        if (resident.isEmpty()) {
            return GeneralResponse.error418("Could not create resident");
        }

        registeredUserService.delete(registeredUser.get());
        residentService.save(resident.get());

        return GeneralResponse.ok("Migrated to resident", null);
    }

    @PostMapping("/finish-register")
    @PreAuthorize("hasRole('ROLE_INVITED')")
    public ResponseEntity<GeneralResponse<RegisteredUserSimpleDto>> finishRegister(@RequestBody FinishRegisterDto finishRegisterDto) {
        Optional<RegisteredUser> registeredUser = registeredUserService.findByEmail(finishRegisterDto.getEmail());

        if (registeredUser.isEmpty()) {
            return GeneralResponse.error404("User not found.");
        }
        InvitedUser invitedUser = (InvitedUser) registeredUser.get();

        invitedUser.setIdentifier(finishRegisterDto.getIdentifier());
        invitedUser.setIdentifierType(finishRegisterDto.getIdentifierType());

        var simpleDto = RegisteredUserSimpleDto.from(invitedUser);

        invitedUserService.save(invitedUser);
        return GeneralResponse.ok("Finished Register", simpleDto);
    }
}
