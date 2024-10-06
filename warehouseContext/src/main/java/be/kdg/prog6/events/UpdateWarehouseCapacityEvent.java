package be.kdg.prog6.events;

import java.util.UUID;

public record UpdateWarehouseCapacityEvent(UUID warehouseId, boolean fullCapacity) {
}
