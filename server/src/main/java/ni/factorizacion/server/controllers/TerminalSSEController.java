package ni.factorizacion.server.controllers;

import ni.factorizacion.server.services.impl.SseService;
import ni.factorizacion.server.utils.CustomSseEmitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sse/terminal")
public class TerminalSSEController {

    @Autowired
    private SseService sseService;

    @GetMapping(path = "/entry", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public CustomSseEmitter getEntry() {
        CustomSseEmitter emitter = sseService.createEmiter("entry-added");
        var initEvent = sseService.createEvent("init", "init");
        emitter.sendEvent(initEvent);
        return emitter;
    }
}
