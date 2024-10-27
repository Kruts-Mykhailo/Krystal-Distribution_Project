package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.PurchaseOrder;

import java.util.List;

public interface PurchaseOrderFoundPort  {
    PurchaseOrder getByPurchaseOrderNumber(String purchaseOrderNumber);
    List<PurchaseOrder> getAllPurchaseOrders();
}
