package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.services.AccessService;
import ni.factorizacion.server.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping("/generate/")
    public ResponseEntity<GeneralResponse<String>> getQrToken() {
        Optional<RegisteredUser> user = authService.getCurrentAuthenticatedUser();

        if (user.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "No user found", null);
        }
        return GeneralResponse.getResponse(HttpStatus.OK, "UserFound" ,accessService.getQrToken(user.get()).getContent());
    }
}
