package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.WarehouseNumber;

import java.time.LocalDateTime;
import java.util.UUID;

public record AdjustInventoryCommand(WarehouseNumber warehouseNumber, LocalDateTime sendTime, Double netWeight) {
}
