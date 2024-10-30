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
    private String sellerName;
    private LocalDateTime receivedDateTime;

    public PurchaseOrderDTO(String orderStatus, String poNumber, LocalDateTime receivedDateTime, String sellerName) {
        this.orderStatus = orderStatus;
        this.poNumber = poNumber;
        this.receivedDateTime = receivedDateTime;
        this.sellerName = sellerName;
    }

}
