package ni.factorizacion.server.controllers;

import jakarta.validation.Valid;
import ni.factorizacion.server.dtos.GeneralResponse;
import ni.factorizacion.server.dtos.SaveUserDto;
import ni.factorizacion.server.dtos.UserSimpleDto;
import ni.factorizacion.server.entities.User;
import ni.factorizacion.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {
    @Autowired
    UserService userService;

    @GetMapping(value = "/api/users/", produces = "application/json")
    public ResponseEntity<GeneralResponse<List<UserSimpleDto>>> getAllUsers() {
        var users = userService.getAll();
        if (users.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "No users found", users);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found users", users);
    }

    @GetMapping(value = "/api/users/{identifier}", produces = "application/json")
    public ResponseEntity<GeneralResponse<User>> getUser(@PathVariable String identifier) {
        var user = userService.findByIdentifier(identifier);
        if (user.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found", null);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Accepted", user.get());
    }

    @PostMapping(value = "/api/users/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<GeneralResponse<User>> saveUser(@Valid @RequestBody SaveUserDto userDto) throws Exception {
        userService.saveUser(userDto);
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "User saved", null);
    }

    @PatchMapping(value = "/api/users/", produces = "application/json", consumes = "application/json")
    public ResponseEntity<GeneralResponse<User>> updateUser(@Valid @RequestBody SaveUserDto userDto) throws Exception {
        userService.updateUser(userDto);
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "User updated", null);
    }

    @DeleteMapping(value = "/api/users/{identifier}", produces = "application/json")
    public ResponseEntity<GeneralResponse<User>> removeUser(@PathVariable String identifier) throws Exception {
        userService.removeUser(identifier);
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "User deleted", null);
    }
}
