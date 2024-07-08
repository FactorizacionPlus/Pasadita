package ni.factorizacion.server.controllers;

import jakarta.validation.Valid;
import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.input.SaveUserDto;
import ni.factorizacion.server.domain.dtos.output.RegisteredUserSimpleDto;
import ni.factorizacion.server.domain.dtos.output.ResidentSimpleDto;
import ni.factorizacion.server.domain.dtos.output.UserSimpleDto;
import ni.factorizacion.server.domain.entities.RegisteredUser;
import ni.factorizacion.server.domain.entities.Resident;
import ni.factorizacion.server.domain.entities.User;
import ni.factorizacion.server.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/users", produces = "application/json")
public class UserRestController {
    @Autowired
    private UserService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<Page<UserSimpleDto>>> getAllUsers(Pageable pageable) {
        Page<User> users = service.findAllAnonymous(pageable);
        if (users.isEmpty()) {
            return GeneralResponse.ok("No anonymous users found", Page.empty());
        }
        Page<UserSimpleDto> userSimpleDtos = users.map(UserSimpleDto::from);
        return GeneralResponse.ok("Anonymous users found", userSimpleDtos);
    }

    @GetMapping(value = "/anonymous/{identifier}")
    public ResponseEntity<GeneralResponse<UserSimpleDto>> getAnonymousUser(@PathVariable String identifier) {
        Optional<User> user = service.findAnonymousByIdentifier(identifier);
        if (user.isEmpty()) {
            return GeneralResponse.error404("Anonymous user not found");
        }
        UserSimpleDto userSimpleDto = UserSimpleDto.from(user.get());
        return GeneralResponse.ok("User found", userSimpleDto);
    }

    @GetMapping(value = "/{identifier}")
    public ResponseEntity<GeneralResponse<UserSimpleDto>> getUser(@PathVariable String identifier) {
        Optional<User> user = service.findByIdentifier(identifier);
        if (user.isEmpty()) {
            return GeneralResponse.error404("Anonymous user not found");
        }
        UserSimpleDto userSimpleDto;
        if (user.get() instanceof Resident) {
            userSimpleDto = ResidentSimpleDto.from((Resident) user.get());
        } else if (user.get() instanceof RegisteredUser) {
            userSimpleDto = RegisteredUserSimpleDto.from((RegisteredUser) user.get());
        } else {
            userSimpleDto = UserSimpleDto.from(user.get());
        }
        return GeneralResponse.ok("User found", userSimpleDto);
    }

    @GetMapping(value = "/everything/everywhere/all-at-once")
    public ResponseEntity<GeneralResponse<Page<UserSimpleDto>>> getAllEverythingUsers(Pageable pageable) {
        Page<User> users = service.findAll(pageable);
        if (users.isEmpty()) {
            return GeneralResponse.ok("No users found", Page.empty());
        }
        Page<UserSimpleDto> userSimpleDtos = users.map((v) -> {
            if (v instanceof RegisteredUser) {
                return RegisteredUserSimpleDto.from((RegisteredUser) v);
            }
            return UserSimpleDto.from(v);
        });
        return GeneralResponse.ok("Users found", userSimpleDtos);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<GeneralResponse<String>> saveUser(@Valid @RequestBody SaveUserDto userDto) {
        Optional<User> found = service.findByIdentifier(userDto.getIdentifier());
        if (found.isPresent()) {
            return GeneralResponse.error409("Anonymous user already exists");
        }

        Optional<User> user = service.createFrom(userDto);
        if (user.isEmpty()) {
            return GeneralResponse.error409("Could not create anonymous user");
        }

        service.save(user.get());

        return GeneralResponse.ok("Anonymous user created", null);
    }

    @PatchMapping(consumes = "application/json")
    public ResponseEntity<GeneralResponse<String>> updateUser(@Valid @RequestBody SaveUserDto userDto) {
        Optional<User> found = service.findByIdentifier(userDto.getIdentifier());
        if (found.isEmpty()) {
            return GeneralResponse.error404("Anonymous user not found");
        }
        service.update(found.get(), userDto);
        service.save(found.get());
        return GeneralResponse.ok("Anonymous user updated", null);
    }
}
