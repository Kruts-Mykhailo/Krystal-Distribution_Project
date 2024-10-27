package be.kdg.prog6.adapter.out.db.purchaseOrder;

import be.kdg.prog6.domain.OrderLine;
import be.kdg.prog6.domain.PONumber;
import be.kdg.prog6.domain.PurchaseOrder;
import be.kdg.prog6.domain.Seller;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderConverter {

    public static PurchaseOrderJpaEntity toPurchaseOrderJpaEntity(PurchaseOrder purchaseOrder) {
        return new PurchaseOrderJpaEntity(
                purchaseOrder.poNumber().number(),
                purchaseOrder.sellerId().id(),
                purchaseOrder.status().name(),
                purchaseOrder.getReceivedDateTime()
        );
    }

    public static PurchaseOrder toPurchaseOrder(PurchaseOrderJpaEntity purchaseOrderJpaEntity, List<OrderLine> orderLines) {
        return new PurchaseOrder(
                new Seller.SellerId(purchaseOrderJpaEntity.getSellerId()),
                orderLines,
                new PONumber(purchaseOrderJpaEntity.getPoNumber()),
                PurchaseOrder.OrderStatus.valueOf(purchaseOrderJpaEntity.getOrderStatus()),
                purchaseOrderJpaEntity.getReceivedDateTime());
    }

    public static PurchaseOrder toPurchaseOrder(PurchaseOrderJpaEntity purchaseOrderJpaEntity) {
        return new PurchaseOrder(
                new Seller.SellerId(purchaseOrderJpaEntity.getSellerId()),
                new ArrayList<>(),
                new PONumber(purchaseOrderJpaEntity.getPoNumber()),
                PurchaseOrder.OrderStatus.valueOf(purchaseOrderJpaEntity.getOrderStatus()),
                purchaseOrderJpaEntity.getReceivedDateTime()

        );
    }
}
