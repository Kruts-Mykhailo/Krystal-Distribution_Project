package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.domain.PurchaseOrder;

public class PurchaseOrderConverter {
    public static PurchaseOrderDTO convert(PurchaseOrder purchaseOrder) {
        return new PurchaseOrderDTO(
                purchaseOrder.status(),
                purchaseOrder.poNumber(),
                purchaseOrder.sellerId()
        );
    }
}
