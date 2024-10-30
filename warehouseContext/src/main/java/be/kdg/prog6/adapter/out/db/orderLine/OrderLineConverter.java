package be.kdg.prog6.adapter.out.db.orderLine;

import be.kdg.prog6.adapter.out.db.purchaseOrder.PurchaseOrderJpaEntity;
import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.OrderLine;
import be.kdg.prog6.domain.UOM;

import java.util.List;
import java.util.stream.Collectors;

public class OrderLineConverter {

    public static OrderLineJpaEntity toJpa(OrderLine orderLine) {
        return new OrderLineJpaEntity(
                orderLine.quantity(),
                orderLine.uom().name(),
                orderLine.materialType().name());
    }

    public static List<OrderLine> toOrderLineList(List<OrderLineJpaEntity> orderLines) {
        return orderLines
                .stream()
                .map(OrderLineConverter::toOrderLine)
                .collect(Collectors.toList());
    }

    public static OrderLine toOrderLine(OrderLineJpaEntity orderLineJpaEntity) {
        return new OrderLine(
                MaterialType.valueOf(orderLineJpaEntity.getMaterialType()),
                orderLineJpaEntity.getAmount(),
                UOM.valueOf(orderLineJpaEntity.getUom())
        );
    }

}
