package co.istad.springwebmvc.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ValidationException {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)


    public Map<String,Object> handleValidationError(MethodArgumentNotValidException ex){
        List<Map<String, Object>> errorList = new ArrayList<>();
        ex.getFieldErrors().stream()
                .forEach(fieldError -> {
                    Map<String, Object> error = new HashMap<>();
                    error.put("field",fieldError.getField());
                    error.put("message",fieldError.getDefaultMessage());
                    errorList.add(error);
                });
        return Map.of("error",errorList);
    }


    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<?> handleServiceError(ResponseStatusException ex){
        return ResponseEntity.status(ex.getStatusCode())
                .body(Map.of("error",ex.getReason()));
    }

}
