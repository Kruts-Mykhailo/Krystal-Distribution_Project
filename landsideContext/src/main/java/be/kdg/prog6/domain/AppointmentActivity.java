package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record AppointmentActivity(UUID activityId, LicensePlate licensePlate, ActivityType activityType, LocalDateTime localDateTime, TruckArrivalStatus status) {

}
