package ni.factorizacion.server.controllers;

import jakarta.validation.Valid;
import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.input.SaveUserDto;
import ni.factorizacion.server.domain.dtos.input.TerminalCreateEntryDto;
import ni.factorizacion.server.domain.entities.*;
import ni.factorizacion.server.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/access")
public class AccessRestController {
    @Autowired
    private AccessService accessService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private EntryService entryService;

    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserRestController userRestController;

    @RequestMapping("/generate")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GeneralResponse<String>> getQrToken() {
        Optional<RegisteredUser> user = authService.getCurrentAuthenticatedUser();

        if (user.isEmpty()) {
            return GeneralResponse.error404("No user found");
        }
        Token token = accessService.getQrToken(user.get());
        return GeneralResponse.ok("Token generated", token.getCode().toString());
    }


    @GetMapping(value = "/validate/{uuid}")
    @PreAuthorize("hasRole('ROLE_TERMINAL')")
    public ResponseEntity<GeneralResponse<String>> validateQrToken(@PathVariable UUID uuid) {
        Optional<Terminal> terminal = authService.getCurrentAuthenticatedTerminal();
        if (terminal.isEmpty()) {
            return GeneralResponse.error404("Incorrect terminal data");
        }

        Optional<Token> token = accessService.findQrToken(uuid);
        if (token.isEmpty()) {
            return GeneralResponse.error404("Invalid QR token");
        }

        boolean isValid = accessService.validateQrToken(token.get());
        if (!isValid) {
            return GeneralResponse.error400("Invalid QR token");
        }

        RegisteredUser user = token.get().getUser();

        Residence residence = null;
        if (user.getClass().equals(Resident.class)) {
            residence = ((Resident) user).getResidence();
        } else if (user.getClass().equals(InvitedUser.class)) {
            Optional<Permission> permission = permissionService.findByUserNow((InvitedUser) user);
            if (permission.isEmpty()) {
                return GeneralResponse.error401("User doesn't have permission");
            }
            residence = permission.get().getResidence();
        }

        Optional<Entry> entry = entryService.createEntry(user, terminal.get(), "", residence);
        if (entry.isEmpty()) {
            return GeneralResponse.error500("Could not create entry");
        }

        return GeneralResponse.ok("Entry created", null);
    }

    @PostMapping("/anonymous/entry")
    @PreAuthorize("hasRole('ROLE_TERMINAL')")
    public ResponseEntity<GeneralResponse<String>> createEntry(@RequestBody @Valid TerminalCreateEntryDto actionDto) {
        Optional<Terminal> currentTerminal = authService.getCurrentAuthenticatedTerminal();
        if (currentTerminal.isEmpty()) {
            return GeneralResponse.error404("Incorrect terminal data");
        }

        Terminal terminal = currentTerminal.get();
        if (terminal.getType() != TerminalType.MANUAL) {
            return GeneralResponse.error401("Not allowed to create an entry");
        }

        Optional<User> user = userService.findByIdentifier(actionDto.getIdentifier());
        if (user.isEmpty()) {
            return GeneralResponse.error404("Anonymous user not found");
        }

        // Ya que un Usuario Anónimo no posee residencia, la entrada no tiene relación con ninguna residencia
        Optional<Entry> entry = entryService.createEntry(user.get(), terminal, actionDto.getDescription(), null);
        if (entry.isEmpty()) {
            return GeneralResponse.error500("Could not create entry");
        }

        return GeneralResponse.ok("Entry created", null);
    }

    @PostMapping("/anonymous/user")
    @PreAuthorize("hasRole('ROLE_TERMINAL')")
    public ResponseEntity<GeneralResponse<String>> createUser(@RequestBody @Valid SaveUserDto saveUserDto) {
        return userRestController.saveUser(saveUserDto);
    }
}
