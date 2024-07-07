package ni.factorizacion.server.utils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Data
public class CustomSseEmitter extends SseEmitter {
    private static final MediaType UTF8_TEXT_STREAM = new MediaType("text", "event-stream", StandardCharsets.UTF_8);
    private String eventName;

    public CustomSseEmitter(Long timeout, String eventName) {
        super(timeout);
        this.eventName = eventName;
    }

    public <T> Set<ResponseBodyEmitter.DataWithMediaType> createEvent(T payload) {
        return SseEmitter.event().data(payload).name(eventName).build();
    }

    public synchronized void sendEvent(Set<ResponseBodyEmitter.DataWithMediaType> event) {
        try {
            this.send(event);
        } catch (IOException exception) {
            this.completeWithError(exception);
        }
    }

    @Override
    protected void extendResponse(ServerHttpResponse outputMessage) {
        HttpHeaders headers = outputMessage.getHeaders();
        if (headers.getContentType() == null) {
            headers.setContentType(UTF8_TEXT_STREAM);
        }
    }
}
