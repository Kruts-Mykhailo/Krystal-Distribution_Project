package be.kdg.prog6.adapter.out.db.purchaseOrder;

import be.kdg.prog6.adapter.exceptions.PurchaseOrderNotFoundException;
import be.kdg.prog6.adapter.out.db.orderLine.OrderLineConverter;
import be.kdg.prog6.adapter.out.db.orderLine.OrderLineJpaRepository;
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

    public PurchaseOrderAdapter(PurchaseOrderJpaRepository purchaseOrderJpaRepository, OrderLineJpaRepository orderLineJpaRepository) {
        this.purchaseOrderJpaRepository = purchaseOrderJpaRepository;
        this.orderLineJpaRepository = orderLineJpaRepository;
    }

    @Override
    public PurchaseOrder getByPurchaseOrderNumber(String purchaseOrderNumber) {
        PurchaseOrderJpaEntity purchaseOrderJpaEntity = purchaseOrderJpaRepository
                .findByPurchaseOrderNumberFetched(purchaseOrderNumber)
                .orElseThrow(() -> new PurchaseOrderNotFoundException(
                                "Purchase order %s not found".formatted(purchaseOrderNumber)));

        List<OrderLine> orderLines = OrderLineConverter.toOrderLineList(purchaseOrderJpaEntity.getOrderLines());

        return PurchaseOrderConverter.toPurchaseOrder(purchaseOrderJpaEntity, orderLines);
    }

    @Override
    public List<PurchaseOrder> getAllPurchaseOrders() {
        return purchaseOrderJpaRepository.findAll()
                .stream()
                .map(PurchaseOrderConverter::toPurchaseOrder)
                .collect(Collectors.toList());
    }

    @Override
    public void savePurchaseOrder(PurchaseOrder purchaseOrder) {
        PurchaseOrderJpaEntity purchaseOrderJpaEntity =
                purchaseOrderJpaRepository.save(PurchaseOrderConverter.toPurchaseOrderJpaEntity(purchaseOrder));

        orderLineJpaRepository.saveAll(OrderLineConverter
                .toOrderLineJpaEntityList(purchaseOrder.orderLines(),purchaseOrderJpaEntity.getPoNumber()));
    }

    @Override
    public void update(PurchaseOrder purchaseOrder, PurchaseOrder.OrderStatus status) {
        PurchaseOrderJpaEntity purchaseOrderJpaEntity = purchaseOrderJpaRepository.findById(purchaseOrder.poNumber().number())
                .orElseThrow(() -> new PurchaseOrderNotFoundException(
                        "Purchase order %s not found".formatted(purchaseOrder.poNumber())));
        purchaseOrderJpaEntity.setOrderStatus(status.name());
        purchaseOrderJpaRepository.save(purchaseOrderJpaEntity);
    }

    @Override
    public void update(PurchaseOrder purchaseOrder) {
        PurchaseOrderJpaEntity purchaseOrderJpaEntity = purchaseOrderJpaRepository.findById(purchaseOrder.poNumber().number())
                .orElseThrow(() -> new PurchaseOrderNotFoundException(
                        "Purchase order %s not found".formatted(purchaseOrder.poNumber())));
        purchaseOrderJpaEntity.setOrderStatus(purchaseOrder.status().name());
        purchaseOrderJpaRepository.save(purchaseOrderJpaEntity);
    }


}
