package ni.factorizacion.server.controllers;

import jakarta.validation.Valid;
import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.input.AssignResidentDto;
import ni.factorizacion.server.domain.dtos.output.ResidenceSimpleDto;
import ni.factorizacion.server.domain.dtos.input.SaveResidenceDto;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.services.ResidenceService;
import ni.factorizacion.server.types.ControlException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/residence")
public class ResidenceRestController {
    @Autowired
    private ResidenceService service;

    @GetMapping
    public ResponseEntity<GeneralResponse<List<ResidenceSimpleDto>>> getAllResidences() {
        List<Residence> residences = service.findAll();
        if (residences.isEmpty()) {
            return GeneralResponse.ok("No residences found", List.of());
        }
        List<ResidenceSimpleDto> residenceSimpleDtos = residences.stream().map(ResidenceSimpleDto::from).toList();
        return GeneralResponse.ok("Found residences", residenceSimpleDtos);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<GeneralResponse<Residence>> saveResidence(@Valid @RequestBody SaveResidenceDto residenceDto) throws Exception {
        service.saveResidence(residenceDto);
        return GeneralResponse.ok("Residence saved", null);
    }

    @DeleteMapping(value = "/{uuid}")
    public void removeResidence(@PathVariable("uuid") String uuid) throws Exception {
        service.removeResidence(uuid);
    }

    @PutMapping(value = "/{uuid}")
    public void updateUser(@PathVariable("uuid") String uuid, @RequestBody @Valid SaveResidenceDto dto) throws Exception {
        service.updateResidence(uuid, dto);
    }

    @PostMapping(consumes = "application/json", value = "/assign")
    public ResponseEntity<GeneralResponse<Residence>> assignResidenteToResidencia(@Valid @RequestBody AssignResidentDto request) throws ControlException {
        service.assignResidenteToResidencia(request);
        return GeneralResponse.ok("Resident assigning to residence", null);
    }

}
