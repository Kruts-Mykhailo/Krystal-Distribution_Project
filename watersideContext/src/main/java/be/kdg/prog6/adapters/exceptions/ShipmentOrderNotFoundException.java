package be.kdg.prog6.adapters.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class ShipmentOrderNotFoundException extends EntityNotFoundException {
    public ShipmentOrderNotFoundException(String message) {
        super(message);
    }
}
