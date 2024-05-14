package ni.factorizacion.server.utils;

import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErrorTools {
    static public Map<String, List<String>> mapError(List<FieldError> errors) {
        Map<String, List<String>> errorMap = new HashMap<>();

        errors.forEach(error -> {
            var _errors = errorMap.computeIfAbsent(error.getField(), key -> new ArrayList<>());
            _errors.add(error.getDefaultMessage());
            errorMap.put(error.getField(), _errors);
        });

        return errorMap;
    }
}
