package be.kdg.prog6.adapter.out.db.purchaseOrder;

import be.kdg.prog6.adapter.exceptions.PurchaseOrderNotFoundException;
import be.kdg.prog6.adapter.out.db.orderLine.OrderLineConverter;
import be.kdg.prog6.adapter.out.db.orderLine.OrderLineJpaEntity;
import be.kdg.prog6.adapter.out.db.orderLine.OrderLineJpaRepository;
import be.kdg.prog6.adapter.out.db.seller.SellerJpaEntity;
import be.kdg.prog6.adapter.out.db.seller.SellerJpaRepository;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.out.PurchaseOrderFoundPort;
import be.kdg.prog6.port.out.PurchaseOrderSavedPort;
import be.kdg.prog6.port.out.PurchaseOrderUpdatedPort;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class PurchaseOrderAdapter implements PurchaseOrderUpdatedPort, PurchaseOrderFoundPort, PurchaseOrderSavedPort {

    private final PurchaseOrderJpaRepository purchaseOrderJpaRepository;
    private final OrderLineJpaRepository orderLineJpaRepository;
    private final SellerJpaRepository sellerJpaRepository;

    public PurchaseOrderAdapter(PurchaseOrderJpaRepository purchaseOrderJpaRepository, OrderLineJpaRepository orderLineJpaRepository, SellerJpaRepository sellerJpaRepository) {
        this.purchaseOrderJpaRepository = purchaseOrderJpaRepository;
        this.orderLineJpaRepository = orderLineJpaRepository;
        this.sellerJpaRepository = sellerJpaRepository;
    }

    @Override
    public PurchaseOrder getByPurchaseOrderNumber(String purchaseOrderNumber) {
        PurchaseOrderJpaEntity purchaseOrderJpaEntity = purchaseOrderJpaRepository
                .findByPurchaseOrderNumberFetched(purchaseOrderNumber)
                .orElseThrow(() -> new PurchaseOrderNotFoundException(
                                "Purchase order %s not found".formatted(purchaseOrderNumber)));

        List<OrderLine> orderLines = OrderLineConverter.toOrderLineList(purchaseOrderJpaEntity.getOrderLines());

        return PurchaseOrderConverter.fromJpaFetched(purchaseOrderJpaEntity, orderLines);
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderJpaRepository.findAllWithSellerFetched()
                .stream()
                .map(PurchaseOrderConverter::toPurchaseOrder)
                .collect(Collectors.toList());
    }

    @Override
    public void savePurchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrderJpaEntity purchaseOrderJpaEntity = PurchaseOrderConverter.toPurchaseOrderJpaEntity(purchaseOrder);
        SellerJpaEntity sellerJpaEntity = sellerJpaRepository.getReferenceById(purchaseOrder.getSeller().getSellerId().id());
        List<OrderLineJpaEntity> orderLineJpaEntities = purchaseOrder.orderLines().stream().map(OrderLineConverter::toJpa).collect(Collectors.toList());

        purchaseOrderJpaEntity.setSeller(sellerJpaEntity);
        purchaseOrderJpaEntity = purchaseOrderJpaRepository.save(purchaseOrderJpaEntity);

        for (OrderLineJpaEntity o : orderLineJpaEntities) {
            o.setPurchaseOrder(purchaseOrderJpaEntity);
        }

        orderLineJpaRepository.saveAll(orderLineJpaEntities);
    }

    @Override
    public void updateStatus(PurchaseOrder purchaseOrder) {
        PurchaseOrderJpaEntity purchaseOrderJpaEntity = purchaseOrderJpaRepository.findById(purchaseOrder.poNumber().number())
                .orElseThrow(() -> new PurchaseOrderNotFoundException(
                        "Purchase order %s not found".formatted(purchaseOrder.poNumber())));
        purchaseOrderJpaEntity.setOrderStatus(purchaseOrder.status().name());
        purchaseOrderJpaRepository.save(purchaseOrderJpaEntity);
    }


}
