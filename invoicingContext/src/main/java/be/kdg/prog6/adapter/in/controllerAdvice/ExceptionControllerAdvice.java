package be.kdg.prog6.adapter.in.controllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = { IllegalArgumentException.class })
    private ErrorResponse handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = { EntityNotFoundException.class })
    private ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    }

}
