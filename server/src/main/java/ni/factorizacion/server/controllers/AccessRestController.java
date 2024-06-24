package ni.factorizacion.server.controllers;

import jakarta.validation.Valid;
import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.TerminalValidateQRDto;
import ni.factorizacion.server.domain.entities.Entry;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Terminal;
import ni.factorizacion.server.domain.entities.Token;
import ni.factorizacion.server.services.AccessService;
import ni.factorizacion.server.services.AuthenticationService;
import ni.factorizacion.server.services.EntryService;
import ni.factorizacion.server.services.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/access/")
public class AccessRestController {
    @Autowired
    private AccessService accessService;

    @Autowired
    private AuthenticationService authService;

    @Autowired
    private EntryService entryService;

    @Autowired
    private TerminalService terminalService;

    @RequestMapping("/generate/")
    public ResponseEntity<GeneralResponse<String>> getQrToken() {
        Optional<RegisteredUser> user = authService.getCurrentAuthenticatedUser();

        if (user.isEmpty()) {
            return GeneralResponse.error404("No user found");
        }
        Token token = accessService.getQrToken(user.get());
        return GeneralResponse.ok("Token generated", token.getContent());
    }


    @PostMapping(value = "/validate/", consumes = "application/json")
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

        Optional<Entry> entry = entryService.createEntry(user, terminal.get());
        if (entry.isEmpty()) {
            return GeneralResponse.error500("Could not create entry");
        }

        return GeneralResponse.ok("Entry created", null);
    }
}
