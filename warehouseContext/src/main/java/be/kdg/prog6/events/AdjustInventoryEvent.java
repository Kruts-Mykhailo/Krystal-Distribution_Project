package be.kdg.prog6.events;

import be.kdg.prog6.domain.WarehouseNumber;

import java.time.LocalDateTime;

public record AdjustInventoryEvent(WarehouseNumber warehouseNumber, LocalDateTime sendTime, Double netWeight) {
}
