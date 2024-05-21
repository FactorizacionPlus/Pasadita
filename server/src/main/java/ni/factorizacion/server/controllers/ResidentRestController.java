package ni.factorizacion.server.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.ResidentSimpleDto;
import ni.factorizacion.server.domain.dtos.SaveResidentDto;
import ni.factorizacion.server.domain.entities.User;
import ni.factorizacion.server.services.ResidentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/residents/", produces = "application/json")
@RequiredArgsConstructor
public class ResidentRestController {
    private final ResidentService service;

    @GetMapping
    public ResponseEntity<GeneralResponse<List<ResidentSimpleDto>>> getAllResidents() {
        var users = service.getAll();
        if (users.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "No residents found", users);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found residents", users);
    }

    @GetMapping(value = "/{identifier}")
    public ResponseEntity<GeneralResponse<ResidentSimpleDto>> getResident(@PathVariable String identifier) {
        var user = service.findByIdentifier(identifier);
        if (user.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, "Resident not found", null);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found", user.get());
    }

    @PostMapping
    public ResponseEntity<GeneralResponse<User>> saveUser(@Valid @RequestBody SaveResidentDto userDto) throws Exception {
        service.saveUser(userDto);
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Resident saved", null);
    }
}
