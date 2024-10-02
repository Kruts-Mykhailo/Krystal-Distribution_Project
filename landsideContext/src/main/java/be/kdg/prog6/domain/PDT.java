package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public record PDT(UUID warehouseId, LocalDateTime sendTime, Double netWeight, MaterialType materialType) {
}
