package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.domain.PurchaseOrder;
import be.kdg.prog6.domain.Seller;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;


@Setter
@Getter
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

}
