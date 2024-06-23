package ni.factorizacion.server.domain.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class GeneralResponse<T> {
    private String message;
    private T data;

    public static <T> ResponseEntity<GeneralResponse<T>> getResponse(HttpStatus status, String message, T data) {
        return new ResponseEntity<>(new GeneralResponse<>(message, data), status);
    }

    public static <T> ResponseEntity<GeneralResponse<T>> error404(String message) {
        return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, message, null);
    }

    public static <T> ResponseEntity<GeneralResponse<T>> error400(String message) {
        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, message, null);
    }

    public static <T> ResponseEntity<GeneralResponse<T>> error401(String message) {
        return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, message, null);
    }

    public static <T> ResponseEntity<GeneralResponse<T>> error409(String message) {
        return GeneralResponse.getResponse(HttpStatus.CONFLICT, message, null);
    }

    public static <T> ResponseEntity<GeneralResponse<T>> error500(String message) {
        return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
    }

    public static <T> ResponseEntity<GeneralResponse<T>> ok(String message, T data) {
        return GeneralResponse.getResponse(HttpStatus.OK, message, data);
    }

}
