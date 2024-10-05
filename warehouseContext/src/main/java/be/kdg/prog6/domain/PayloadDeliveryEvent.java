package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public record PayloadDeliveryEvent(Double amount, LocalDateTime eventDateTime) implements PayloadActivity {

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
