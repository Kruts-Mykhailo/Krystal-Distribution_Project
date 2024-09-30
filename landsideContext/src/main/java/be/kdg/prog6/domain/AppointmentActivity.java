package be.kdg.prog6.domain;

import java.time.LocalDateTime;

public record AppointmentActivity(LicensePlate licensePlate, ActivityType activityType, LocalDateTime localDateTime, AppointmentStatus status) {
}
