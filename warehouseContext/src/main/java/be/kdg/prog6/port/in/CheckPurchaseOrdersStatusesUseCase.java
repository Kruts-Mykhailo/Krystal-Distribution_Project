package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.PurchaseOrder;

import java.util.List;

public interface CheckPurchaseOrdersStatusesUseCase {
    List<PurchaseOrder> getAllPurchaseOrders();
}
