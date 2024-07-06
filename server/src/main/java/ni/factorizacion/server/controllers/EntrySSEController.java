package ni.factorizacion.server.controllers;

import ni.factorizacion.server.domain.entities.Residence;
import ni.factorizacion.server.domain.entities.TerminalType;
import ni.factorizacion.server.services.ResidenceService;
import ni.factorizacion.server.services.impl.SseService;
import ni.factorizacion.server.utils.CustomSseEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/sse/entry")
public class EntrySSEController {
    @Autowired
    private SseService sseService;

    @Autowired
    private ResidenceService residenceService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public CustomSseEmitter getAllEntries() {
        CustomSseEmitter emitter = sseService.createEmiter("full-entry-added");
        var initEvent = sseService.createEvent("init", "init");
        emitter.sendEvent(initEvent);
        return emitter;
    }

    @GetMapping(path = "/terminal/{terminalType}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public CustomSseEmitter getAllEntriesByTerminal(@PathVariable TerminalType terminalType) {
        CustomSseEmitter emitter = sseService.createEmiter("entry-added-" + terminalType);
        var initEvent = sseService.createEvent("init", "init");
        emitter.sendEvent(initEvent);
        return emitter;
    }

    @GetMapping(path = "/residence/{residenceUuid}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public CustomSseEmitter getAllEntriesByResidence(@PathVariable UUID residenceUuid) {
        Optional<Residence> residence = residenceService.findById(residenceUuid);
        if (residence.isEmpty()) {
            return null;
        }

        CustomSseEmitter emitter = sseService.createEmiter("entry-added-" + residenceUuid.toString());
        var initEvent = sseService.createEvent("init", "init");
        emitter.sendEvent(initEvent);
        return emitter;
    }
}