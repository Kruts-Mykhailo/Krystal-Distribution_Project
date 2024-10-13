package be.kdg.prog6.domain;

import java.time.LocalDateTime;

public record CommissionFee(Seller.SellerId sellerId,
                            Double feeAmount,
                            String poNumber,
                            LocalDateTime calculationDateTime,
                            FeeStatus status) {
    public enum FeeStatus {
        CREATED, PAYED
    }
}
