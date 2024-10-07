package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.ActivityType;

import java.time.LocalDateTime;
import java.util.UUID;

public record PayloadCommand(ActivityType activityType, Double amount, UUID warehouseId, LocalDateTime timestamp) {
}
