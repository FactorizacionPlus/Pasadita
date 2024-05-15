package ni.factorizacion.server.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ni.factorizacion.server.dtos.GeneralResponse;
import ni.factorizacion.server.dtos.SaveUserDto;
import ni.factorizacion.server.dtos.UserSimpleDto;
import ni.factorizacion.server.entities.User;
import ni.factorizacion.server.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService service;

    @GetMapping(value = "/api/users/")
    public ResponseEntity<GeneralResponse<List<UserSimpleDto>>> getAllUsers() {
        var users = service.getAll();
        if (users.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "No anonymous users found", users);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found anonymous users", users);
    }

    @GetMapping(value = "/api/users/{identifier}")
    public ResponseEntity<GeneralResponse<User>> getUser(@PathVariable String identifier) {
        var user = service.findByIdentifier(identifier);
        if (user.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Anonymous user not found", null);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Accepted", user.get());
    }

    @PostMapping(value = "/api/users/", consumes = "application/json")
    public ResponseEntity<GeneralResponse<User>> saveUser(@Valid @RequestBody SaveUserDto userDto) throws Exception {
        service.saveUser(userDto);
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Anonymous user saved", null);
    }

    @PatchMapping(value = "/api/users/", consumes = "application/json")
    public ResponseEntity<GeneralResponse<User>> updateUser(@Valid @RequestBody SaveUserDto userDto) throws Exception {
        service.updateUser(userDto);
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Anonymous user updated", null);
    }

    @DeleteMapping(value = "/api/users/{identifier}")
    public ResponseEntity<GeneralResponse<User>> removeUser(@PathVariable String identifier) throws Exception {
        service.removeUser(identifier);
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Anonymous user deleted", null);
    }
}
