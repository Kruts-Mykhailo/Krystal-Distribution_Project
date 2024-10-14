package be.kdg.prog6.events;

import java.util.Objects;

public record ChangePOStatusEvent(String poNumber) {
    public ChangePOStatusEvent {
        Objects.requireNonNull(poNumber);

        if (!poNumber.startsWith("PO")) {
            throw new IllegalArgumentException("PO number must start with 'PO'");
        }
    }
}
