package be.kdg.prog6.core;

import be.kdg.prog6.adapter.exceptions.PurchaseOrderExistsException;
import be.kdg.prog6.adapter.exceptions.PurchaseOrderNotFoundException;
import be.kdg.prog6.domain.PurchaseOrder;
import be.kdg.prog6.port.in.ReceivePOUseCase;
import be.kdg.prog6.port.out.PurchaseOrderFoundPort;
import be.kdg.prog6.port.out.PurchaseOrderSavedPort;
import org.springframework.stereotype.Service;

@Service
public class ReceivePOUseCaseImpl implements ReceivePOUseCase {

    private final PurchaseOrderSavedPort purchaseOrderSavedPort;
    private final PurchaseOrderFoundPort purchaseOrderFoundPort;

    public ReceivePOUseCaseImpl(PurchaseOrderSavedPort purchaseOrderSavedPort, PurchaseOrderFoundPort purchaseOrderFoundPort) {
        this.purchaseOrderSavedPort = purchaseOrderSavedPort;
        this.purchaseOrderFoundPort = purchaseOrderFoundPort;
    }

    @Override
    public void receivePO(PurchaseOrder purchaseOrder) {
        try {
            purchaseOrderFoundPort.getByPurchaseOrderNumber(purchaseOrder.poNumber().number());
            throw new PurchaseOrderExistsException(
                    "Purchase order %s already exists".formatted(
                            purchaseOrder.poNumber().number()));

        } catch (PurchaseOrderNotFoundException e) {
            purchaseOrderSavedPort.savePurchaseOrder(purchaseOrder);
        }

    }
}
