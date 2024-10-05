package be.kdg.prog6.adapter.in.messaging;

import java.util.UUID;

public record WarehouseUpdatedEvent(UUID warehouseId, boolean fullCapacity) {
}
