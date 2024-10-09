package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.LicensePlate;

import java.time.LocalDateTime;
import java.util.Objects;

public record TruckArrivalCommand(LicensePlate licensePlate, LocalDateTime arrivalTime) {
    public TruckArrivalCommand {
        Objects.requireNonNull(licensePlate.licensePlate());
//        if (arrivalTime.isBefore(LocalDateTime.now())) {
//            throw new IllegalArgumentException("Arrival date cannot be in the past.");
//        }
    }
}
