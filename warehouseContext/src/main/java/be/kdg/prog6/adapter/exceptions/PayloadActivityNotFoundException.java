package be.kdg.prog6.adapter.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class PayloadActivityNotFoundException extends EntityNotFoundException {
    public PayloadActivityNotFoundException(String message) {
        super(message);
    }
}
