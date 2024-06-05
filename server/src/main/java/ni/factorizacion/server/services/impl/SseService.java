package ni.factorizacion.server.services.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseService {

    private final Collection<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public SseEmitter createEmiter() {
        SseEmitter emitter = new SseEmitter(Long.MAX_VALUE);
        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError(throwable -> {
            emitters.remove(emitter);
            emitter.completeWithError(throwable);
        });

        emitters.add(emitter);

        return emitter;
    }

    public <T> void sendEvent(T payload, String eventName) {
        var event = SseEmitter.event().data(payload).name(eventName).build();

        for (SseEmitter emitter : emitters) {
            try {
                emitter.send(event);
            } catch (IOException exception) {
                emitter.completeWithError(exception);
            }
        }
    }
}
