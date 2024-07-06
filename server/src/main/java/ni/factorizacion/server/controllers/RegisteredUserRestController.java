package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.input.FinishRegisterDto;
import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Resident;
import ni.factorizacion.server.services.InvitedUserService;
import ni.factorizacion.server.services.RegisteredUserService;
import ni.factorizacion.server.services.ResidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/migrate/invited")
    public ResponseEntity<GeneralResponse<String>> migrateUserToInvited(@RequestBody String identifier) {
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

    @PostMapping("/migrate/resident")
    public ResponseEntity<GeneralResponse<String>> migrateUserToResident(@RequestBody String identifier) {
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

    @PreAuthorize("hasRole('ROLE_INVITED')")
    @PostMapping("/finish-register")
    public ResponseEntity<GeneralResponse<String>> finishRegister(@RequestBody FinishRegisterDto finishRegisterDto) {
        Optional<RegisteredUser> registeredUser = registeredUserService.findByEmail(finishRegisterDto.getEmail());

        if (registeredUser.isEmpty()) {
            return GeneralResponse.error404("User not found.");
        }

        registeredUser.get().setIdentifier(finishRegisterDto.getIdentifier());
        registeredUser.get().setIdentifierType(finishRegisterDto.getIdentifierType());

        Optional<InvitedUser> invitedUser = invitedUserService.createFrom(registeredUser.get());

        invitedUserService.save(invitedUser.orElse(null));
        return GeneralResponse.ok("Finished Register", null);
    }
}
