package be.kdg.prog6.adapter.exceptions;

import jakarta.persistence.EntityNotFoundException;

public class TruckWeightRecordNotFoundException extends EntityNotFoundException {
    public TruckWeightRecordNotFoundException(String message) {
        super(message);
    }
}
