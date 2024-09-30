package be.kdg.prog6.domain;

import java.time.LocalDateTime;

public record TruckWeightRecord(LicensePlate licensePlate, Double weight, LocalDateTime time) {
}
