package be.kdg.prog6.adapter.in.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class WarehouseNotFoundException extends EntityNotFoundException {
    public WarehouseNotFoundException(String message) {
        super(message);
    }
}
