package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.Seller;

import java.time.LocalDateTime;

public interface CalculateStorageCostsUseCase {
    Double calculate(Seller.SellerId sellerId);
}
