package ni.factorizacion.server.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.URI;

@Data
@AllArgsConstructor
public class GeneralResponse<T> {
    private String message;
    private T data;

    public static <T> ResponseEntity<GeneralResponse<T>> getResponse(HttpStatus status, String message, T data) {
        return new ResponseEntity<>(new GeneralResponse<>(message, data), status);
    }

    public static <T> ResponseEntity<GeneralResponse<T>> redirectTo(String url, String message, T data) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(url));

        return new ResponseEntity<>(new GeneralResponse<>(message, data), headers, HttpStatus.MOVED_PERMANENTLY);
    }
}
