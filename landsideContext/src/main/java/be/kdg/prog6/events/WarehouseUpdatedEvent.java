package be.kdg.prog6.events;

import java.util.UUID;

public record WarehouseUpdatedEvent(UUID warehouseId, boolean fullCapacity) {
}
