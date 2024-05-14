package ni.factorizacion.server;

import ni.factorizacion.server.dtos.GeneralResponse;
import ni.factorizacion.server.utils.ErrorTools;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<GeneralResponse<Map<String, List<String>>>> handleMethodArgumentsNotValid(MethodArgumentNotValidException ex) {
        var bindingResult = ex.getBindingResult();
        var errors = bindingResult.getFieldErrors();

        var errorsMap = ErrorTools.mapErrors(errors);

        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, "Field errors", errorsMap);
    }
}