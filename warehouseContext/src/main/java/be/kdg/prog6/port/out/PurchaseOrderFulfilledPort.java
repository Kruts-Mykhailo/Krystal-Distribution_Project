package be.kdg.prog6.port.out;

import be.kdg.prog6.events.CalculateCommissionForPurchaseOrderEvent;

public interface PurchaseOrderFulfilledPort {
    void sendInfoForCommission(CalculateCommissionForPurchaseOrderEvent CalculateCommissionForPurchaseOrderEvent);
}
