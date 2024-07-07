package ni.factorizacion.server.services.impl;

import ni.factorizacion.server.utils.CustomSseEmitter;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseService {

    private final Collection<CustomSseEmitter> emitters = new CopyOnWriteArrayList<>();

    public CustomSseEmitter createEmiter(String eventName) {
        CustomSseEmitter emitter = new CustomSseEmitter(Long.MAX_VALUE, eventName);

        emitter.onCompletion(() -> emitters.remove(emitter));
        emitter.onTimeout(() -> emitters.remove(emitter));
        emitter.onError(throwable -> {
            emitters.remove(emitter);
            emitter.completeWithError(throwable);
        });

        emitters.add(emitter);

        return emitter;
    }

    public <T> Set<ResponseBodyEmitter.DataWithMediaType> createEvent(T payload, String eventName) {
        return SseEmitter.event().data(payload).name(eventName).build();
    }

    public <T> void sendEvent(T payload, String eventName) {
        var event = createEvent(payload, "");
        var namedEvent = createEvent(payload, eventName);

        for (CustomSseEmitter emitter : emitters) {
            try {
                if (emitter.getEventName().equals(eventName)) {
                    if (eventName.equals("entry-added")) {
                        emitter.send(namedEvent);
                    } else {
                        emitter.send(event);
                    }
                }
            } catch (IOException exception) {
                emitter.completeWithError(exception);
            }
        }
    }
}
