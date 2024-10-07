package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.PurchaseOrder;

public interface ReceivePOUseCase {
    void receivePO(PurchaseOrder purchaseOrder);
}
