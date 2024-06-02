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
    AccessService accessService;

    @Autowired
    AuthenticationService authService;

    @Autowired
    private EntryService entryService;

    @Autowired
    private TerminalService terminalService;

    @RequestMapping("/generate/")
    public ResponseEntity<GeneralResponse<String>> getQrToken() {
        Optional<RegisteredUser> user = authService.getCurrentAuthenticatedUser();

        if (user.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "No user found", null);
        }
        return GeneralResponse.getResponse(HttpStatus.OK, "UserFound" ,accessService.getQrToken(user.get()).getContent());
    }

    @PostMapping(value = "/validate/", consumes = "application/json")
    public ResponseEntity<GeneralResponse<String>> validateQrToken(@RequestBody @Valid TerminalValidateQRDto actionDto) {
        Optional<Terminal> terminal = terminalService.findTerminalByType(actionDto.getTerminalType(), actionDto.getPassword());
        if (terminal.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Incorrect terminal data", null);
        }

        Optional<Token> token = accessService.findQrToken(actionDto.getTokenContent());
        if (token.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Invalid token", null);
        }

        boolean isValid = accessService.validateQrToken(token.get());
        if (!isValid) {
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Invalid token", null);
        }

        RegisteredUser user = token.get().getUser();

        Optional<Entry> entry = entryService.createEntry(user, terminal.get());
        if (entry.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Couldn't create entry", null);
        }

        return GeneralResponse.getResponse(HttpStatus.OK, "Entry created", null);
    }
}
