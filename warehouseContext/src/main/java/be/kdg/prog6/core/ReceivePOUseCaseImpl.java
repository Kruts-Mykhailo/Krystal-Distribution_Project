package be.kdg.prog6.core;

import be.kdg.prog6.adapter.exceptions.PurchaseOrderExistsException;
import be.kdg.prog6.adapter.exceptions.PurchaseOrderNotFoundException;
import be.kdg.prog6.domain.PurchaseOrder;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.port.in.ReceivePOUseCase;
import be.kdg.prog6.port.out.PurchaseOrderFoundPort;
import be.kdg.prog6.port.out.PurchaseOrderSavedPort;
import be.kdg.prog6.port.out.SellerFoundPort;
import org.springframework.stereotype.Service;

@Service
public class ReceivePOUseCaseImpl implements ReceivePOUseCase {

    private final PurchaseOrderSavedPort purchaseOrderSavedPort;
    private final PurchaseOrderFoundPort purchaseOrderFoundPort;
    private final SellerFoundPort sellerFoundPort;

    public ReceivePOUseCaseImpl(PurchaseOrderSavedPort purchaseOrderSavedPort, PurchaseOrderFoundPort purchaseOrderFoundPort, SellerFoundPort sellerFoundPort) {
        this.purchaseOrderSavedPort = purchaseOrderSavedPort;
        this.purchaseOrderFoundPort = purchaseOrderFoundPort;
        this.sellerFoundPort = sellerFoundPort;
    }

    @Override
    public void receivePO(PurchaseOrder purchaseOrder) {
        try {
            purchaseOrderFoundPort.getByPurchaseOrderNumber(purchaseOrder.poNumber().number());
            throw new PurchaseOrderExistsException(
                    "Purchase order %s already exists".formatted(
                            purchaseOrder.poNumber().number()));

        } catch (PurchaseOrderNotFoundException e) {

            Seller seller = sellerFoundPort.getById(purchaseOrder.getSeller().getSellerId());
            purchaseOrder.setSeller(seller);

            purchaseOrderSavedPort.savePurchaseOrder(purchaseOrder);
        }

    }
}
