package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.PurchaseOrder;

public interface PurchaseOrderSavedPort {
    void savePurchaseOrder(PurchaseOrder purchaseOrder);
}
