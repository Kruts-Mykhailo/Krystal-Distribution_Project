package be.kdg.prog6.adapter.in.warehouseProjection;

import java.util.UUID;

public record WarehouseUpdatedEvent(UUID warehouseId, boolean fullCapacity) {
}
