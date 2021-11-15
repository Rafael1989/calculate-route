package pt.rafael.pwc.calculateroute.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import pt.rafael.pwc.calculateroute.model.CalculateRouteNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CalculateRouteNotFoundException.class)
    public ResponseEntity handleRouteNotFoundException(CalculateRouteNotFoundException ex) {
        return new ResponseEntity("Route not found", BAD_REQUEST);
    }
}
