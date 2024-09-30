package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.LicensePlate;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record PassBridgeCommand(LicensePlate licensePlate, Double weight, LocalDateTime time) {
    public PassBridgeCommand {
        if (weight == null) {
            throw new IllegalArgumentException("Weight cannot be null");
        }
        if (weight < 0.25) {
            throw new IllegalArgumentException("Weight cannot be less than 0.25 tons");
        }
    }
}
