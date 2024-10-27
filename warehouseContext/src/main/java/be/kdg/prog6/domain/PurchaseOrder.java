package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public final class PurchaseOrder {
    private final Seller.SellerId sellerId;
    private final List<OrderLine> orderLines;
    private final PONumber poNumber;
    private OrderStatus status;
    private final LocalDateTime receivedDateTime;

    public PurchaseOrder(Seller.SellerId sellerId, List<OrderLine> orderLines, PONumber poNumber, OrderStatus status, LocalDateTime receivedDateTime) {
        this.sellerId = sellerId;
        this.orderLines = orderLines;
        this.poNumber = poNumber;
        this.status = status;
        this.receivedDateTime = receivedDateTime;
    }

    public boolean isNotFilled() {
        return !status.equals(OrderStatus.FILLED);
    }

    public boolean isOutstanding() {
        return status.equals(OrderStatus.OUTSTANDING);
    }

    public void fillOrder() {
        this.status = OrderStatus.FILLED;
    }

    public Seller.SellerId sellerId() {
        return sellerId;
    }

    public List<OrderLine> orderLines() {
        return orderLines;
    }

    public PONumber poNumber() {
        return poNumber;
    }

    public OrderStatus status() {
        return status;
    }

    public LocalDateTime getReceivedDateTime() {
        return receivedDateTime;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (PurchaseOrder) obj;
        return Objects.equals(this.sellerId, that.sellerId) &&
                Objects.equals(this.orderLines, that.orderLines) &&
                Objects.equals(this.poNumber, that.poNumber) &&
                Objects.equals(this.status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerId, orderLines, poNumber, status);
    }

    @Override
    public String toString() {
        return "PurchaseOrder[" +
                "sellerId=" + sellerId + ", " +
                "orderLines=" + orderLines + ", " +
                "poNumber=" + poNumber + ", " +
                "status=" + status + ']';
    }


    public enum OrderStatus {
        OUTSTANDING, MATCHED, FILLED
    }
}
