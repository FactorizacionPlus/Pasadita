package ni.factorizacion.server.types;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ControlException extends Exception {
    private final HttpStatus status;

    public ControlException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }
}
