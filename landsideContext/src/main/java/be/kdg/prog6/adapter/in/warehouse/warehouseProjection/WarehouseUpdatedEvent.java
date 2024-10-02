package be.kdg.prog6.adapter.in.warehouse.warehouseProjection;

import java.util.UUID;

public record WarehouseUpdatedEvent(UUID warehouseId, boolean fullCapacity) {
}
