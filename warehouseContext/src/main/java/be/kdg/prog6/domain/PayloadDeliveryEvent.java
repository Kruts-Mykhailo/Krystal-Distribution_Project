package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public record PayloadDeliveryEvent(Double amount, LocalDateTime eventDateTime) implements PayloadActivityRecord {

    public PayloadDeliveryEvent {
        Objects.requireNonNull(amount);
        if (amount < 0) {
            throw new IllegalArgumentException("Value should not be less than 0");
        }
    }

    @Override
    public Double payload() {
        return -Math.abs(amount);
    }
}
