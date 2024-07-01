package ni.factorizacion.server.controllers;

import jakarta.validation.Valid;
import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.input.SaveUserDto;
import ni.factorizacion.server.domain.dtos.input.TerminalCreateEntryDto;
import ni.factorizacion.server.domain.dtos.input.TerminalValidateQRDto;
import ni.factorizacion.server.domain.entities.*;
import ni.factorizacion.server.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

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
    private TerminalService terminalService;
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
        return GeneralResponse.ok("Token generated", token.getContent());
    }


    @PostMapping(value = "/validate", consumes = "application/json")
    public ResponseEntity<GeneralResponse<String>> validateQrToken(@RequestBody @Valid TerminalValidateQRDto actionDto) {
        Optional<Terminal> terminal = terminalService.findTerminalByType(actionDto.getTerminalType(), actionDto.getPassword());
        if (terminal.isEmpty()) {
            return GeneralResponse.error404("Incorrect terminal data");
        }

        Optional<Token> token = accessService.findQrToken(actionDto.getTokenContent());
        if (token.isEmpty()) {
            return GeneralResponse.error404("Invalid QR token");
        }

        boolean isValid = accessService.validateQrToken(token.get());
        if (!isValid) {
            return GeneralResponse.error400("Invalid QR token");
        }

        RegisteredUser user = token.get().getUser();

        // TODO: Obtener la residencia del permiso actual para el Usuario Invitado
        Residence residence = null;
        if (user.getClass().equals(Resident.class)) {
            residence = ((Resident) user).getResidence();
        }

        Optional<Entry> entry = entryService.createEntry(user, terminal.get(), "", residence);
        if (entry.isEmpty()) {
            return GeneralResponse.error500("Could not create entry");
        }

        return GeneralResponse.ok("Entry created", null);
    }

    @PostMapping("/anonymous/entry")
    public ResponseEntity<GeneralResponse<String>> createEntry(@RequestBody @Valid TerminalCreateEntryDto actionDto) {
        if (actionDto.getTerminalType() != TerminalType.MANUAL) {
            return GeneralResponse.error401("Not allowed to create an entry");
        }

        Optional<Terminal> terminal = terminalService.findTerminalByType(actionDto.getTerminalType(), actionDto.getPassword());
        if (terminal.isEmpty()) {
            return GeneralResponse.error404("Incorrect terminal data");
        }

        Optional<User> user = userService.findByIdentifier(actionDto.getIdentifier());
        if (user.isEmpty()) {
            return GeneralResponse.error404("Anonymous user not found");
        }

        // Ya que un Usuario Anónimo no posee residencia, la entrada no tiene relación con ninguna residencia
        Optional<Entry> entry = entryService.createEntry(user.get(), terminal.get(), actionDto.getDescription(), null);
        if (entry.isEmpty()) {
            return GeneralResponse.error500("Could not create entry");
        }

        return GeneralResponse.ok("Entry created", null);
    }

    @PostMapping("/anonymous/user")
    public ResponseEntity<GeneralResponse<String>> createUser(@RequestBody @Valid SaveUserDto saveUserDto) {
        return userRestController.saveUser(saveUserDto);
    }
}
