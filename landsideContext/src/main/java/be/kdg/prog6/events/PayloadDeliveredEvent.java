package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.domain.MaterialType;

import java.time.LocalDateTime;
import java.util.UUID;

public record PayloadDeliveredEvent(UUID warehouseId, LocalDateTime sendTime, Double netWeight, MaterialType materialType) {
}
