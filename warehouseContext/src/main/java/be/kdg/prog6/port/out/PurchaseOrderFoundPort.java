package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.PurchaseOrder;

public interface PurchaseOrderFoundPort  {
    PurchaseOrder matchByPurchaseOrderNumber(String purchaseOrderNumber);
}
