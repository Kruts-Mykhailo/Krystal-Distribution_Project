package be.kdg.prog6.adapter.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class SellerNotFoundException extends EntityNotFoundException {
    public SellerNotFoundException(String message) {
        super(message);
    }
}
