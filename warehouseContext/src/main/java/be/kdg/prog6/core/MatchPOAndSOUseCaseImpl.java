package be.kdg.prog6.core;

import be.kdg.prog6.domain.PONumber;
import be.kdg.prog6.domain.PurchaseOrder;
import be.kdg.prog6.port.in.MatchPOAndSOUseCase;
import be.kdg.prog6.port.out.PurchaseOrderFoundPort;
import be.kdg.prog6.port.out.PurchaseOrderUpdatedPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class MatchPOAndSOUseCaseImpl implements MatchPOAndSOUseCase {

    private final PurchaseOrderFoundPort purchaseOrderFoundPort;
    private final PurchaseOrderUpdatedPort purchaseOrderUpdatedPort;

    public MatchPOAndSOUseCaseImpl(PurchaseOrderFoundPort purchaseOrderFoundPort, PurchaseOrderUpdatedPort purchaseOrderUpdatedPort) {
        this.purchaseOrderFoundPort = purchaseOrderFoundPort;
        this.purchaseOrderUpdatedPort = purchaseOrderUpdatedPort;
    }

    @Override
    @Transactional
    public void matchOrders(PONumber poNumber) {
        PurchaseOrder purchaseOrder = purchaseOrderFoundPort.getByPurchaseOrderNumber(poNumber.number());
        if (purchaseOrder.isOutstanding()) {
            purchaseOrder.matchOrder();
            purchaseOrderUpdatedPort.updateStatus(purchaseOrder);
        }

    }
}
