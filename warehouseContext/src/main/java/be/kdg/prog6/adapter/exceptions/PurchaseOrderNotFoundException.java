package be.kdg.prog6.adapter.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class PurchaseOrderNotFoundException extends EntityNotFoundException {
    public PurchaseOrderNotFoundException(String message) {
        super(message);
    }
}
