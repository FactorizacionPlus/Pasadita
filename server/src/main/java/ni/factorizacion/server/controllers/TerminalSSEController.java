package ni.factorizacion.server.controllers;

import ni.factorizacion.server.services.impl.SseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/sse/terminal")
public class TerminalSSEController {

    @Autowired
    private SseService sseService;

    @GetMapping(path = "/entry", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter getEntry() {
        SseEmitter emitter = sseService.createEmiter();
        sseService.sendEvent("init", "init");
        return emitter;
    }
}
