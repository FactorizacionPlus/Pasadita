package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.entities.Configuration;
import ni.factorizacion.server.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<GeneralResponse<String>> updateConfiguration(Long timeFrame, Long qrExpiration) {
        Configuration configuration = service.getConfiguration();
        configuration.setTimeframe(timeFrame);
        configuration.setQrExpiration(qrExpiration);
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
