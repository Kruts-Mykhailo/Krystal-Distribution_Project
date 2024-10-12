package be.kdg.prog6.events;

import java.util.Objects;

public record ShipOperationsCompletedEvent(String poNumber) {
    public ShipOperationsCompletedEvent {
        Objects.requireNonNull(poNumber);

        if (!poNumber.startsWith("PO")) {
            throw new IllegalArgumentException("PO number must start with 'PO'");
        }
    }
}
