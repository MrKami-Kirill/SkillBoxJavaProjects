package main.mapper;

import javassist.NotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.ParseException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class ExceptionMapper extends RuntimeException {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<String> handleRunTimeException(NotFoundException e) {
        return ResponseEntity.status(NOT_FOUND).body(e.getMessage());
    }

    @ExceptionHandler({NullPointerException.class})
    public ResponseEntity<String> handleRunTimeException(NullPointerException e) {
        return ResponseEntity.status(NOT_EXTENDED).body(e.getMessage());
    }
}
