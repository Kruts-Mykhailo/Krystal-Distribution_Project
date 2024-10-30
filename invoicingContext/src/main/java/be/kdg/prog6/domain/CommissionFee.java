package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public final class CommissionFee {
    private final Seller.SellerId sellerId;
    private final Double feeAmount;
    private final PONumber poNumber;
    private final LocalDateTime calculationDateTime;
    private final FeeStatus status;

    public CommissionFee(Seller.SellerId sellerId,
                         Double feeAmount,
                         PONumber poNumber,
                         LocalDateTime calculationDateTime,
                         FeeStatus status) {
        this.sellerId = sellerId;
        this.feeAmount = feeAmount;
        this.poNumber = poNumber;
        this.calculationDateTime = calculationDateTime;
        this.status = status;
    }

    public CommissionFee(Seller.SellerId sellerId, Double feeAmount, PONumber poNumber) {
        this.sellerId = sellerId;
        this.feeAmount = feeAmount;
        this.poNumber = poNumber;
        this.calculationDateTime = LocalDateTime.now();
        this.status = FeeStatus.CREATED;
    }

    public Seller.SellerId sellerId() {
        return sellerId;
    }

    public Double feeAmount() {
        return feeAmount;
    }

    public PONumber poNumber() {
        return poNumber;
    }

    public LocalDateTime calculationDateTime() {
        return calculationDateTime;
    }

    public FeeStatus status() {
        return status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (CommissionFee) obj;
        return Objects.equals(this.sellerId, that.sellerId) &&
                Objects.equals(this.feeAmount, that.feeAmount) &&
                Objects.equals(this.poNumber, that.poNumber) &&
                Objects.equals(this.calculationDateTime, that.calculationDateTime) &&
                Objects.equals(this.status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerId, feeAmount, poNumber, calculationDateTime, status);
    }

    @Override
    public String toString() {
        return "CommissionFee[" +
                "sellerId=" + sellerId + ", " +
                "feeAmount=" + feeAmount + ", " +
                "poNumber=" + poNumber + ", " +
                "calculationDateTime=" + calculationDateTime + ", " +
                "status=" + status + ']';
    }


    public enum FeeStatus {
        CREATED, PAYED
    }
}
