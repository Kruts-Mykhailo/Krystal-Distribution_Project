package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.domain.PurchaseOrder;
import be.kdg.prog6.domain.Seller;

import java.time.LocalDateTime;
import java.util.UUID;

public class PurchaseOrderDTO {
    private String orderStatus;
    private String poNumber;
    private UUID sellerId;
    private LocalDateTime receivedDateTime;

    public PurchaseOrderDTO(String orderStatus, String poNumber, UUID sellerId, LocalDateTime receivedDateTime) {
        this.orderStatus = orderStatus;
        this.poNumber = poNumber;
        this.sellerId = sellerId;
        this.receivedDateTime = receivedDateTime;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }
}
