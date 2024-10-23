package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record PDT(WarehouseNumber warehouseNumber, LocalDateTime sendTime, MaterialType materialType) {
}
