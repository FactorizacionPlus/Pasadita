package ni.factorizacion.server.controllers;

import jakarta.validation.Valid;
import ni.factorizacion.server.domain.dtos.*;
import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.services.ResidenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/residence/ ")
public class ResidenceRestController {
    @Autowired
    private ResidenceService service;

    @GetMapping
    public ResponseEntity<GeneralResponse<List<ResidenceSimpleDto>>> getAllResidences() {
        var residences = service.getAll();
        if (residences.isEmpty()) {
            return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "No residences found", residences);
        }
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Found residences", residences);
    }

    @PostMapping
    public ResponseEntity<GeneralResponse<Residence>> saveResidence(@Valid @RequestBody SaveResidenceDto residenceDto) throws Exception {
        service.saveResidence(residenceDto);
        return GeneralResponse.getResponse(HttpStatus.ACCEPTED, "Residence saved", null);
    }

    @DeleteMapping(path = "/{uuid}")
    public void removeResidence(@PathVariable("uuid") String uuid) throws Exception {
        service.removeResidence(uuid);
    }

    @PutMapping(path = "/{uuid}")
    public void updateUser(@PathVariable("uuid") String uuid, @RequestBody @Valid SaveResidenceDto dto) throws Exception {
        service.updateResidence(uuid, dto);
    }
}
