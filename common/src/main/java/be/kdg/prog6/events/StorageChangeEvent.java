package be.kdg.prog6.events;


import java.time.LocalDateTime;
import java.util.UUID;

public record StorageChangeEvent(UUID sellerId, Double tons, LocalDateTime arrivalTime, String materialType) {
}