package ni.factorizacion.server.controllers;

import lombok.AllArgsConstructor;
import ni.factorizacion.server.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserRestController {
    private final UserService userService;

    @GetMapping(value = "/api/users", produces = "application/json")
    public List<String> getBooks() {
        return userService.getAll();
    }
}
