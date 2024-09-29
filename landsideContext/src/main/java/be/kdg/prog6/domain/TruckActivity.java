package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record TruckActivity(LicensePlate licensePlate, ActivityType activityType, LocalDateTime localDateTime, AppointmentStatus status) {
}
