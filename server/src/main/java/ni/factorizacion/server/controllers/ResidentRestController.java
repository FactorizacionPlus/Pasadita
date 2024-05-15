package ni.factorizacion.server.controllers;

import lombok.RequiredArgsConstructor;
import ni.factorizacion.server.dtos.GeneralResponse;
import ni.factorizacion.server.dtos.ResidentSimpleDto;
import ni.factorizacion.server.entities.User;
import ni.factorizacion.server.services.ResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/residents/", produces = "application/json")
@RequiredArgsConstructor
public class ResidentRestController {
    private final ResidentService service;

    @GetMapping
    public ResponseEntity<GeneralResponse<List<ResidentSimpleDto>>> getAllUsers() {
        var users = service.getAll();
        if (users.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "No users found", users);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found users", users);
    }

    @GetMapping(value = "/{identifier}")
    public ResponseEntity<GeneralResponse<ResidentSimpleDto>> getUser(@PathVariable String identifier) {
        var user = service.findByIdentifier(identifier);
        if (user.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "User not found", null);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Accepted", user.get());
    }
}
