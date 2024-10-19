package be.kdg.prog6.events;

import java.util.UUID;

public record WarehouseCapacityChangeEvent(UUID warehouseId, Double initialCapacity, String operationType) {
}
