package be.kdg.prog6.adapters.out.db.purchaseOrder;

import be.kdg.prog6.adapters.exceptions.PurchaseOrderNotFoundException;
import be.kdg.prog6.adapters.out.db.orderLine.OrderLineConverter;
import be.kdg.prog6.adapters.out.db.orderLine.OrderLineJpaRepository;
import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.OrderLine;
import be.kdg.prog6.domain.PO;
import be.kdg.prog6.domain.UOM;
import be.kdg.prog6.ports.out.FindPOPort;
import be.kdg.prog6.ports.out.SavePOPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class PurchaseOrderAdapter implements SavePOPort, FindPOPort {

    private final PurchaseOrderJpaRepository purchaseOrderJpaRepository;
    private final OrderLineJpaRepository orderLineJpaRepository;

    public PurchaseOrderAdapter(PurchaseOrderJpaRepository purchaseOrderJpaRepository, OrderLineJpaRepository orderLineJpaRepository) {
        this.purchaseOrderJpaRepository = purchaseOrderJpaRepository;
        this.orderLineJpaRepository = orderLineJpaRepository;
    }


    @Override
    public void savePO(PO po) {
        PurchaseOrderJpaEntity purchaseOrderJpaEntity =
                purchaseOrderJpaRepository.save(PurchaseOrderConverter.toPurchaseOrderJpaEntity(po));

        orderLineJpaRepository.saveAll(OrderLineConverter
                .toOrderLineJpaEntityList(po.orderLines(),purchaseOrderJpaEntity.getPoNumber()));
    }

    @Override
    public PO findPOByReferenceNumber(String poNumber) {
        PurchaseOrderJpaEntity purchaseOrderJpaEntity = purchaseOrderJpaRepository.findByPurchaseOrderNumberFetched(poNumber)
                .orElseThrow(() -> new PurchaseOrderNotFoundException("Purchase order %s not found".formatted(poNumber)));
        List<OrderLine> orderLines = purchaseOrderJpaEntity.getOrderLines().stream().map(
                orderLineJpaEntity -> new OrderLine(
                        MaterialType.valueOf(orderLineJpaEntity.getMaterialType()),
                        orderLineJpaEntity.getAmount(),
                        UOM.valueOf(orderLineJpaEntity.getUom())
                )
        ).collect(Collectors.toList());
        return PurchaseOrderConverter.toPurchaseOrder(purchaseOrderJpaEntity, orderLines);
    }
}

