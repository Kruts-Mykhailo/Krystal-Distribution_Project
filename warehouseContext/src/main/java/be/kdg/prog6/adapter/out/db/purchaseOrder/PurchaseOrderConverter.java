package be.kdg.prog6.adapter.out.db.purchaseOrder;

import be.kdg.prog6.adapter.out.db.seller.SellerConverter;
import be.kdg.prog6.domain.OrderLine;
import be.kdg.prog6.domain.PONumber;
import be.kdg.prog6.domain.PurchaseOrder;

import java.util.ArrayList;
import java.util.List;

public class PurchaseOrderConverter {

    public static PurchaseOrderJpaEntity toPurchaseOrderJpaEntity(PurchaseOrder purchaseOrder) {
        return new PurchaseOrderJpaEntity(
                purchaseOrder.poNumber().number(),
                purchaseOrder.status().name(),
                purchaseOrder.getReceivedDateTime()
        );
    }

    public static PurchaseOrder fromJpaFetched(PurchaseOrderJpaEntity purchaseOrderJpaEntity, List<OrderLine> orderLines) {
        return new PurchaseOrder(
                SellerConverter.fromJpa(purchaseOrderJpaEntity.getSeller()),
                orderLines,
                new PONumber(purchaseOrderJpaEntity.getPoNumber()),
                PurchaseOrder.OrderStatus.valueOf(purchaseOrderJpaEntity.getOrderStatus()),
                purchaseOrderJpaEntity.getReceivedDateTime());
    }

    public static PurchaseOrder toPurchaseOrder(PurchaseOrderJpaEntity purchaseOrderJpaEntity) {
        return new PurchaseOrder(
                SellerConverter.fromJpa(purchaseOrderJpaEntity.getSeller()),
                new ArrayList<>(),
                new PONumber(purchaseOrderJpaEntity.getPoNumber()),
                PurchaseOrder.OrderStatus.valueOf(purchaseOrderJpaEntity.getOrderStatus()),
                purchaseOrderJpaEntity.getReceivedDateTime()

        );
    }
}
