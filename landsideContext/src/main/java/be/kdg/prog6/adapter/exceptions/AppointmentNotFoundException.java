package be.kdg.prog6.adapter.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class AppointmentNotFoundException extends EntityNotFoundException {
    public AppointmentNotFoundException(String message) {
        super(message);
    }
}
