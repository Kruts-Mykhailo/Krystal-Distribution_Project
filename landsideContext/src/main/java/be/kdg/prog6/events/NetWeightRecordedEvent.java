package be.kdg.prog6.events;

import be.kdg.prog6.domain.LicensePlate;

public record NetWeightRecordedEvent(LicensePlate licensePlate, Double weight) {
}
