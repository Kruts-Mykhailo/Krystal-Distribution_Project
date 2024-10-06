package be.kdg.prog6.domain;

import java.time.LocalDateTime;

public record MaterialAmount(Double amount, LocalDateTime calculationDateTime) {
    public MaterialAmount {
        if (amount < 0) {
            throw new IllegalArgumentException("Calculated amount cannot be negative");
        }
    }
}
