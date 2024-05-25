package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.services.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/")
public class AuthRestController {

    @Autowired
    AuthenticationService service;

    @RequestMapping("/login/github")
    public ResponseEntity<GeneralResponse<Object>> loginGithub(@RequestParam("code") String code) {
        String token = service.getGithubToken(code);
        return GeneralResponse.getResponse(HttpStatus.OK, "Data", token);
    }

    @RequestMapping("/login/google")
    public ResponseEntity<GeneralResponse<Object>> loginGoogle(@RequestParam("code") String code) {
        String token = service.getGoogleToken(code);
        return GeneralResponse.getResponse(HttpStatus.OK, "Data", token);
    }
}
