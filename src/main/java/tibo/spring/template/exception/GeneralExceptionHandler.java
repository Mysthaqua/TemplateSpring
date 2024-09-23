package tibo.spring.template.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;

@ControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> noSuchElementExceptionHandler(NoSuchElementException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Map<String, String>>> handleBindErrors(MethodArgumentNotValidException e) {
        List<Map<String, String>> errorList = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> Map.of(
                        fieldError.getField(),
                        Objects.requireNonNull(fieldError.getDefaultMessage(), "Invalid value")))
                .toList();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorList);
    }
}
