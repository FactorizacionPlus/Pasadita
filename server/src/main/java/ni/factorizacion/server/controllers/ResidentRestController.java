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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<GeneralResponse<Page<ResidentSimpleDto>>> getAllResidents(Pageable pageable) {
        Page<Resident> residents = residentService.findAll(pageable);
        if (residents.isEmpty()) {
            return GeneralResponse.ok("No residents found", Page.empty());
        }
        Page<ResidentSimpleDto> residenceSimpleDtos = residents.map(ResidentSimpleDto::from);
        return GeneralResponse.ok("Found residents", residenceSimpleDtos);
    }

    @GetMapping("/residence/{uuid}")
    public ResponseEntity<GeneralResponse<Page<ResidentSimpleDto>>> getAllResidentsByResidence(@PathVariable UUID uuid, Pageable pageable) {
        Optional<Residence> residence = residenceService.findById(uuid);
        if (residence.isEmpty()) {
            return GeneralResponse.error404("No residence found");
        }

        Page<Resident> residents = residentService.findAllByResidence(residence.get(), pageable);
        if (residents.isEmpty()) {
            return GeneralResponse.ok("No residents found", Page.empty());
        }
        Page<ResidentSimpleDto> residenceSimpleDtos = residents.map(ResidentSimpleDto::from);
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
