package be.kdg.prog6.adapter.out.db.purchaseOrder;

import be.kdg.prog6.domain.OrderLine;
import be.kdg.prog6.domain.PurchaseOrder;
import be.kdg.prog6.domain.Seller;

import java.util.List;

public class PurchaseOrderConverter {

    public static PurchaseOrderJpaEntity toPurchaseOrderJpaEntity(PurchaseOrder purchaseOrder) {
        return new PurchaseOrderJpaEntity(
                purchaseOrder.poNumber(),
                purchaseOrder.sellerId().id(),
                purchaseOrder.status().name()
        );
    }

    public static PurchaseOrder toPurchaseOrder(PurchaseOrderJpaEntity purchaseOrderJpaEntity, List<OrderLine> orderLines) {
        return new PurchaseOrder(
                new Seller.SellerId(purchaseOrderJpaEntity.getSellerId()),
                orderLines,
                purchaseOrderJpaEntity.getPoNumber(),
                PurchaseOrder.OrderStatus.valueOf(purchaseOrderJpaEntity.getOrderStatus())
        );
    }
}
