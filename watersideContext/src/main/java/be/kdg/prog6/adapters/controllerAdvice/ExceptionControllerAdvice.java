package be.kdg.prog6.adapters.controllerAdvice;

import be.kdg.prog6.adapters.exceptions.BunkeringOperationDayLimitException;
import be.kdg.prog6.adapters.exceptions.MatchSOAndPOFailedException;
import be.kdg.prog6.adapters.exceptions.VesselAlreadyLeftException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(value = { EntityNotFoundException.class })
    private ErrorResponse handleEntityNotFoundException(EntityNotFoundException ex, WebRequest request) {
        return ErrorResponse.create(ex, HttpStatus.NOT_FOUND, ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = { BunkeringOperationDayLimitException.class })
    private ErrorResponse handleBunkeringOperationDayLimitException(BunkeringOperationDayLimitException ex, WebRequest request) {
        return ErrorResponse.create(ex, HttpStatus.FORBIDDEN, ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = { MatchSOAndPOFailedException.class })
    private ErrorResponse handleMatchSOAndPOFailedException(MatchSOAndPOFailedException ex, WebRequest request) {
        return ErrorResponse.create(ex, HttpStatus.BAD_REQUEST, ex.getLocalizedMessage());
    }

    @ExceptionHandler(value = { VesselAlreadyLeftException.class })
    private ErrorResponse handleVesselAlreadyLeftException(VesselAlreadyLeftException ex, WebRequest request) {
        return ErrorResponse.create(ex, HttpStatus.GONE, ex.getLocalizedMessage());
    }



}
