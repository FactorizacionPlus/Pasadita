package ni.factorizacion.server;

import jakarta.validation.ConstraintViolationException;
import ni.factorizacion.server.domain.dtos.GeneralResponse;
import ni.factorizacion.server.types.ControlException;
import ni.factorizacion.server.utils.ErrorTools;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ControlException.class)
    protected ResponseEntity<GeneralResponse<Object>> handleControlException(ControlException ex) {
        return GeneralResponse.getResponse(ex.getStatus(), ex.getMessage(), null);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<GeneralResponse<Object>> handleNotFound(ControlException ex) {
        return GeneralResponse.getResponse(ex.getStatus(), ex.getMessage(), null);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<GeneralResponse<Object>> handleAuthException(AuthenticationException ex) {
        return GeneralResponse.getResponse(HttpStatus.UNAUTHORIZED, ex.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<GeneralResponse<Object>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        if (ex.getRequiredType() == null) {
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
        }
        if (ex.getRequiredType().isAssignableFrom(UUID.class)) {
            return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Invalid UUID", null);
        }
        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<GeneralResponse<Object>> handleViolation(ConstraintViolationException ex) {
        var errors = ex.getConstraintViolations();
        var errorsMap = ErrorTools.mapConstraintErrors(errors);
        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Field errors", errorsMap);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<GeneralResponse<Map<String, List<String>>>> handleMethodArgumentsNotValid(MethodArgumentNotValidException ex) {
        var bindingResult = ex.getBindingResult();
        var errors = bindingResult.getFieldErrors();

        var errorsMap = ErrorTools.mapFieldErrors(errors);

        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Field errors", errorsMap);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<GeneralResponse<Object>> handleException(Exception ex) {
        return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
    }
}