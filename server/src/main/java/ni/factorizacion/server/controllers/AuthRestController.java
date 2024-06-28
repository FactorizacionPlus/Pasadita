package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.output.RegisteredUserSimpleDto;
import ni.factorizacion.server.domain.entities.InvitedUser;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Token;
import ni.factorizacion.server.services.AuthenticationService;
import ni.factorizacion.server.services.InvitedUserService;
import ni.factorizacion.server.services.RegisteredUserService;
import ni.factorizacion.server.types.ControlException;
import ni.factorizacion.server.types.GoogleUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

//Test
@RestController
@RequestMapping("/auth")
public class AuthRestController {

    @Autowired
    AuthenticationService authService;

    @Autowired
    RegisteredUserService userService;
    @Autowired
    InvitedUserService invitedUserService;

    @RequestMapping("/login/google")
    public ResponseEntity<GeneralResponse<String>> loginGoogle(@RequestParam("code") String code, @RequestParam("redirect_uri") String redirectUri) throws ControlException {
        String googleToken = authService.getGoogleToken(code, redirectUri);

        Optional<GoogleUserInfo> userInfo = authService.getUserInfoFromToken(googleToken);
        if (userInfo.isEmpty()) {
            return GeneralResponse.error401("No user information found");
        }

        Optional<RegisteredUser> user = userService.findByEmail(userInfo.get().getEmail());
        if (user.isEmpty()) {
            return GeneralResponse.error401("No user found");
        }

        Token token = authService.registerToken(user.get());

        return GeneralResponse.ok("Auth token", token.getContent());
    }

    @RequestMapping("/register/google")
    public ResponseEntity<GeneralResponse<String>> registerGoogle(@RequestParam("code") String code, @RequestParam("redirect_uri") String redirectUri) throws ControlException {
        String googleToken = authService.getGoogleToken(code, redirectUri);

        Optional<GoogleUserInfo> userInfo = authService.getUserInfoFromToken(googleToken);
        if (userInfo.isEmpty()) {
            return GeneralResponse.error401("No user information found");
        }

        InvitedUser user = invitedUserService.createUser(userInfo.get());

        Token token = authService.registerToken(user);

        return GeneralResponse.ok("Auth token", token.getContent());
    }

    @RequestMapping("/self")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<GeneralResponse<RegisteredUserSimpleDto>> getUserInfo() {
        Optional<RegisteredUser> registeredUser = authService.getCurrentAuthenticatedUser();
        if (registeredUser.isEmpty()) {
            return GeneralResponse.error401("No authenticated user");
        }

        RegisteredUserSimpleDto userSimpleDto = RegisteredUserSimpleDto.from(registeredUser.get());

        return GeneralResponse.ok("Found user", userSimpleDto);
    }
}
