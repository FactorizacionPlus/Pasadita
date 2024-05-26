package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.Token;
import ni.factorizacion.server.services.AuthenticationService;
import ni.factorizacion.server.services.InvitedUserService;
import ni.factorizacion.server.types.ControlException;
import ni.factorizacion.server.types.GoogleUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/auth/")
public class AuthRestController {

    @Autowired
    AuthenticationService authService;

    @Autowired
    InvitedUserService userService;


    @RequestMapping("/login/github")
    public ResponseEntity<GeneralResponse<Object>> loginGithub(@RequestParam("code") String code) {
        String token = authService.getGithubToken(code);
        return GeneralResponse.getResponse(HttpStatus.OK, "Data", token);
    }

    @RequestMapping("/login/google")
    public ResponseEntity<GeneralResponse<String>> loginGoogle(@RequestParam("code") String code) throws ControlException {
        String googleToken = authService.getGoogleToken(code);

        Optional<GoogleUserInfo> userInfo = authService.getUserInfoFromToken(googleToken);
        if (userInfo.isEmpty()) {
            throw new ControlException(HttpStatus.UNAUTHORIZED, "No user information found");
        }

        Optional<InvitedUser> user = userService.findByEmail(userInfo.get().getEmail());
        if (user.isEmpty()) {
            throw new ControlException(HttpStatus.UNAUTHORIZED, "No user found");
        }

        Token token = authService.registerToken(user.get());

        return GeneralResponse.getResponse(HttpStatus.OK, "Auth token", token.getContent());
    }
}
