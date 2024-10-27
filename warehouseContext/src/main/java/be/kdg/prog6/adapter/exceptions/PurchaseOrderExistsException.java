package be.kdg.prog6.adapter.exceptions;

import jakarta.persistence.EntityExistsException;

public class PurchaseOrderExistsException extends EntityExistsException {
    public PurchaseOrderExistsException(String message) {
        super(message);
    }
}
