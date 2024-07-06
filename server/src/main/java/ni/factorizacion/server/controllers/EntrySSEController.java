package ni.factorizacion.server.controllers;

import com.fasterxml.jackson.core.json.UTF8StreamJsonParser;
import ni.factorizacion.server.services.impl.SseService;
import ni.factorizacion.server.utils.CustomSseEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sse/entry")
public class EntrySSEController {
    @Autowired
    private SseService sseService;

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public CustomSseEmitter getAllEntries() {
        CustomSseEmitter emitter = sseService.createEmiter("full-entry-added");
        var initEvent = sseService.createEvent("init", "init");
        emitter.sendEvent(initEvent);
        return emitter;
    }
}