package be.kdg.prog6.adapters.out.db.purchaseOrder;

import be.kdg.prog6.domain.OrderLine;
import be.kdg.prog6.domain.PO;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderConverter {

    public static PurchaseOrderJpaEntity toPurchaseOrderJpaEntity(PO purchaseOrder) {
        return new PurchaseOrderJpaEntity(
                purchaseOrder.poNumber(),
                purchaseOrder.sellerId(),
                purchaseOrder.poStatus().name()
        );
    }

    public static PO toPurchaseOrder(PurchaseOrderJpaEntity purchaseOrderJpaEntity, List<OrderLine> orderLines) {
        return new PO(
                purchaseOrderJpaEntity.getSellerId(),
                orderLines,
                purchaseOrderJpaEntity.getPoNumber(),
                PO.POStatus.valueOf(purchaseOrderJpaEntity.getOrderStatus()));
    }

    public static PO toPurchaseOrder(PurchaseOrderJpaEntity purchaseOrderJpaEntity) {
        return new PO(
                purchaseOrderJpaEntity.getSellerId(),
                new ArrayList<>(),
                purchaseOrderJpaEntity.getPoNumber(),
                PO.POStatus.valueOf(purchaseOrderJpaEntity.getOrderStatus()));
    }
}
