package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.domain.dtos.output.TerminalSimpleDto;
import ni.factorizacion.server.domain.entities.Terminal;
import ni.factorizacion.server.services.TerminalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.http.ResponseEntity;



import java.util.List;


@RestController
@RequestMapping(value = "/api/terminals")
public class TerminalRestController {

    @Autowired
    private TerminalService service;

    @GetMapping
    public ResponseEntity<GeneralResponse<List<TerminalSimpleDto>>> getAllTerminals() {
        List<Terminal> terminals = service.findAll();
        if (terminals.isEmpty()) {
            return GeneralResponse.ok("No terminals found", List.of());
        }
        List<TerminalSimpleDto> terminalSimpleDtos = terminals.stream().map(TerminalSimpleDto::from).toList();
        return GeneralResponse.ok("Found terminals", terminalSimpleDtos);
    }
}
