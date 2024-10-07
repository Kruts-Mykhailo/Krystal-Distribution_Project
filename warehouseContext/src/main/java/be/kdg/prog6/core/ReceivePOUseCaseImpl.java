package be.kdg.prog6.core;

import be.kdg.prog6.domain.PurchaseOrder;
import be.kdg.prog6.port.in.ReceivePOUseCase;
import be.kdg.prog6.port.out.PurchaseOrderSavedPort;
import org.springframework.stereotype.Service;

@Service
public class ReceivePOUseCaseImpl implements ReceivePOUseCase {

    private final PurchaseOrderSavedPort purchaseOrderSavedPort;

    public ReceivePOUseCaseImpl(PurchaseOrderSavedPort purchaseOrderSavedPort) {
        this.purchaseOrderSavedPort = purchaseOrderSavedPort;
    }

    @Override
    public void receivePO(PurchaseOrder purchaseOrder) {
        purchaseOrderSavedPort.savePurchaseOrder(purchaseOrder);

    }
}
