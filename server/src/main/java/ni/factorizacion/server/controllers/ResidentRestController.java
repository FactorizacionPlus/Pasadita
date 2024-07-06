package ni.factorizacion.server.controllers;

import jakarta.validation.Valid;
import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.input.ResidentChangeRolDto;
import ni.factorizacion.server.domain.dtos.input.SaveResidentDto;
import ni.factorizacion.server.domain.dtos.output.ResidentSimpleDto;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.Resident;
import ni.factorizacion.server.domain.entities.User;
import ni.factorizacion.server.services.ResidenceService;
import ni.factorizacion.server.services.ResidentService;
import ni.factorizacion.server.utils.UUIDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/residents")
public class ResidentRestController {
    @Autowired
    private ResidentService residentService;

    @Autowired
    private ResidenceService residenceService;

    @GetMapping
    public ResponseEntity<GeneralResponse<List<ResidentSimpleDto>>> getAllResidents() {
        List<Resident> residents = residentService.findAll();
        if (residents.isEmpty()) {
            return GeneralResponse.ok("No residents found", List.of());
        }
        List<ResidentSimpleDto> residenceSimpleDtos = residents.stream().map(ResidentSimpleDto::from).toList();
        return GeneralResponse.ok("Found residents", residenceSimpleDtos);
    }

    @GetMapping("/residence/{residence}")
    public ResponseEntity<GeneralResponse<List<ResidentSimpleDto>>> getAllResidentsByResidence(@PathVariable("residence") String residenceString) {
        Optional<UUID> uuid = UUIDUtils.fromString(residenceString);
        if (uuid.isEmpty()) {
            return GeneralResponse.error400("Invalid residence code");
        }
        Optional<Residence> residence = residenceService.findById(uuid.get());
        if (residence.isEmpty()) {
            return GeneralResponse.error404("No residence found");
        }

        List<Resident> residents = residentService.findAllByResidence(residence.get());
        if (residents.isEmpty()) {
            return GeneralResponse.ok("No residents found", List.of());
        }
        List<ResidentSimpleDto> residenceSimpleDtos = residents.stream().map(ResidentSimpleDto::from).toList();
        return GeneralResponse.ok("Found residents", residenceSimpleDtos);
    }

    @GetMapping(value = "/{identifier}")
    public ResponseEntity<GeneralResponse<ResidentSimpleDto>> getResident(@PathVariable String identifier) {
        Optional<Resident> user = residentService.findByIdentifier(identifier);
        if (user.isEmpty()) {
            return GeneralResponse.error404("Resident not found");
        }
        ResidentSimpleDto residentDto = ResidentSimpleDto.from(user.get());
        return GeneralResponse.ok("Found resident", residentDto);
    }

    @PostMapping
    public ResponseEntity<GeneralResponse<User>> saveUser(@Valid @RequestBody SaveResidentDto userDto) throws Exception {
        residentService.saveUser(userDto);
        return GeneralResponse.ok("Resident saved", null);
    }

    @PostMapping(value = "/role", consumes = "application/json")
    public ResponseEntity<GeneralResponse<String>> updateResidentRole(@Valid @RequestBody ResidentChangeRolDto changeRolDto) {
        Optional<Resident> resident = residentService.findByIdentifier(changeRolDto.getIdentifier());

        if (resident.isEmpty()) {
            return GeneralResponse.error404("Resident not found");
        }
        residentService.changeResidentRole(resident.get(), changeRolDto.getResidentRole());
        return GeneralResponse.ok("Resident role updated", null);
    }
}
