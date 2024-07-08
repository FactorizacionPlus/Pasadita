package ni.factorizacion.server.controllers;

import jakarta.validation.Valid;
import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.input.ConfigurationDto;
import ni.factorizacion.server.domain.entities.Configuration;
import ni.factorizacion.server.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/configuration/")
public class ConfigurationController {
    @Autowired
    private ConfigurationService service;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<Configuration>> getConfiguration() {
        Configuration configuration = service.getConfiguration();
        return GeneralResponse.ok("Configuration found", configuration);
    }

    @PatchMapping(consumes = "application/json")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<String>> updateConfiguration(@Valid @RequestBody ConfigurationDto configurationDto) {
        Configuration configuration = service.getConfiguration();
        configuration.setTimeframe(configurationDto.getTimeframe());
        configuration.setQrExpiration(configurationDto.getQrExpiration());
        service.updateConfiguration(configuration);
        return GeneralResponse.ok("Configuration updated", null);
    }

    @GetMapping("/reset")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<GeneralResponse<String>> resetConfiguration() {
        service.resetConfiguration();
        return GeneralResponse.ok("Configuration reset", null);
    }

}
