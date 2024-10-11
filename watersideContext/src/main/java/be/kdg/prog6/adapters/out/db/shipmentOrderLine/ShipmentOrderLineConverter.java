package be.kdg.prog6.adapters.out.db.shipmentOrderLine;

import be.kdg.prog6.adapters.out.db.shipmentOrder.ShipmentOrderJpaEntity;
import be.kdg.prog6.domain.OrderLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ShipmentOrderLineConverter {
    public static List<ShipmentOrderLineJpaEntity> toEntityList(List<OrderLine> shipmentOrderLines, String soId) {
        return shipmentOrderLines.stream().map( sol ->
                new ShipmentOrderLineJpaEntity(
                        sol.materialType().name(),
                        sol.uom().name(),
                        sol.quantity(),
                        new ShipmentOrderJpaEntity(soId)
                )
        ).collect(Collectors.toList());
    }
}
