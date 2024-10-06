package be.kdg.prog6.events;

import be.kdg.prog6.domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public record PDTReceivedEvent(UUID warehouseId, LocalDateTime sendTime, Double netWeight, MaterialType materialType) {
}
