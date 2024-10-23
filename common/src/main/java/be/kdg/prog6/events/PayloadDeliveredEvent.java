package be.kdg.prog6.events;

import java.time.LocalDateTime;
import java.util.UUID;

public record PayloadDeliveredEvent(String warehouseNumber, LocalDateTime sendTime, Double netWeight, String materialType) {
}
