package be.kdg.prog6.events;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;

import java.time.LocalDateTime;

public record PayloadArrivedEvent(Seller.SellerId sellerId,
                                  Double tons,
                                  LocalDateTime arrivalTime,
                                  MaterialType materialType) {
}
