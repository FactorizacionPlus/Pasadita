package ni.factorizacion.server.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.SaveUserDto;
import ni.factorizacion.server.domain.dtos.UserSimpleDto;
import ni.factorizacion.server.domain.entities.User;
import ni.factorizacion.server.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(produces = "application/json")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService service;

    @GetMapping(value = "/api/users/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<List<UserSimpleDto>>> getAllUsers() {
        List<User> users = service.findAll();
        if (users.isEmpty()) {
            return GeneralResponse.ok("No anonymous users found", List.of());
        }
        List<UserSimpleDto> userSimpleDtos = users.stream().map(UserSimpleDto::from).toList();
        return GeneralResponse.ok("Anonymous users found", userSimpleDtos);
    }

    @GetMapping(value = "/api/users/{identifier}")
    public ResponseEntity<GeneralResponse<UserSimpleDto>> getUser(@PathVariable String identifier) {
        Optional<User> user = service.findByIdentifier(identifier);
        if (user.isEmpty()) {
            return GeneralResponse.error404("Anonymous user not found");
        }
        UserSimpleDto userSimpleDto = UserSimpleDto.from(user.get());
        return GeneralResponse.ok("User found", userSimpleDto);
    }

    @PostMapping(value = "/api/users/", consumes = "application/json")
    public ResponseEntity<GeneralResponse<User>> saveUser(@Valid @RequestBody SaveUserDto userDto) throws Exception {
        service.saveUser(userDto);
        return GeneralResponse.ok("Anonymous user saved", null);
    }

    @PatchMapping(value = "/api/users/", consumes = "application/json")
    public ResponseEntity<GeneralResponse<User>> updateUser(@Valid @RequestBody SaveUserDto userDto) throws Exception {
        service.updateUser(userDto);
        return GeneralResponse.ok("Anonymous user updated", null);
    }

    @DeleteMapping(value = "/api/users/{identifier}")
    public ResponseEntity<GeneralResponse<User>> removeUser(@PathVariable String identifier) throws Exception {
        service.removeUser(identifier);
        return GeneralResponse.ok("Anonymous user deleted", null);
    }
}
