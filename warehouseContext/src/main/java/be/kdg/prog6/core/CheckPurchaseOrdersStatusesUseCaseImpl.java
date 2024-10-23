package be.kdg.prog6.core;

import be.kdg.prog6.domain.PurchaseOrder;
import be.kdg.prog6.port.in.CheckPurchaseOrdersStatusesUseCase;
import be.kdg.prog6.port.out.PurchaseOrderFoundPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckPurchaseOrdersStatusesUseCaseImpl implements CheckPurchaseOrdersStatusesUseCase {

    private final PurchaseOrderFoundPort purchaseOrderFoundPort;

    public CheckPurchaseOrdersStatusesUseCaseImpl(PurchaseOrderFoundPort purchaseOrderFoundPort) {
        this.purchaseOrderFoundPort = purchaseOrderFoundPort;
    }

    @Override
    @Transactional
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderFoundPort.getAllPurchaseOrders();
    }
}
