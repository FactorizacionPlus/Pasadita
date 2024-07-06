package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.TerminalType;
import ni.factorizacion.server.services.impl.SseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.UUID;

@RestController
@RequestMapping("/sse/entry")
public class EntrySSEController {
    @Autowired
    private SseService sseService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getEntry() {
        SseEmitter emitter = sseService.createEmiter();
        sseService.sendEvent("init", "init");
        return emitter;
    }
    @GetMapping(value="terminal/{terminalType}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getByTerminalType(@PathVariable("terminalType") TerminalType terminalType) {
        SseEmitter emitter = sseService.createEmiter();
        sseService.sendEvent("init", "init");
        return emitter;
    }
    @GetMapping(value="terminal/{residence}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getByResidence(@PathVariable("residence")UUID uuid) {

        SseEmitter emitter = sseService.createEmiter();
        sseService.sendEvent("init", "init");
        return emitter;
    }

}
