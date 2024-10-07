package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.PurchaseOrder;

public interface PurchaseOrderUpdatedPort {
    void update(PurchaseOrder purchaseOrder, PurchaseOrder.OrderStatus status);
}
