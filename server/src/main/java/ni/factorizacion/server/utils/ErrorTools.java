package ni.factorizacion.server.utils;

import jakarta.validation.ConstraintViolation;
import org.springframework.validation.FieldError;

import java.util.*;

public class ErrorTools {
    static public Map<String, List<String>> mapFieldErrors(List<FieldError> errors) {
        Map<String, List<String>> errorMap = new HashMap<>();

        errors.forEach(error -> {
            var _errors = errorMap.computeIfAbsent(error.getField(), key -> new ArrayList<>());
            _errors.add(error.getDefaultMessage());
            errorMap.put(error.getField(), _errors);
        });

        return errorMap;
    }
    static public Map<String, List<String>> mapConstraintErrors(Set<ConstraintViolation<?>> exceptions) {
        Map<String, List<String>> errorMap = new HashMap<>();

        exceptions.forEach(error -> {
            var _errors = errorMap.computeIfAbsent(error.getPropertyPath().toString(), key -> new ArrayList<>());
            _errors.add(error.getMessage());
            errorMap.put(error.getPropertyPath().toString(), _errors);
        });

        return errorMap;
    }
}
