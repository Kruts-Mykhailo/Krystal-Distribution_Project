package be.kdg.prog6.events;

import java.util.UUID;

public record WarehouseCapacityChangeEvent(String number, Double initialCapacity, String operationType) {
}
