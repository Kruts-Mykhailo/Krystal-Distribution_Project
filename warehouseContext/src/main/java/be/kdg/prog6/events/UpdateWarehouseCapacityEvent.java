package be.kdg.prog6.events;

import be.kdg.prog6.domain.ActivityType;

import java.util.UUID;

public record UpdateWarehouseCapacityEvent(UUID warehouseId, Double initialCapacity, ActivityType activityType) {
}
