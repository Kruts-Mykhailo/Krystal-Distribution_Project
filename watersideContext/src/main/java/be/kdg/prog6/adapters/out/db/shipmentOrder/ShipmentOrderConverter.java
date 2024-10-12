package be.kdg.prog6.adapters.out.db.shipmentOrder;
import be.kdg.prog6.adapters.out.db.shipmentOrderLine.ShipmentOrderLineJpaEntity;
import be.kdg.prog6.domain.*;

import java.util.ArrayList;
import java.util.List;

public class ShipmentOrderConverter {

    public static ShipmentOrderJpaEntity toShipmentOrderJpaEntity(ShipmentOrder shipmentOrder) {
        return new ShipmentOrderJpaEntity(
                shipmentOrder.getPoReferenceNumber(),
                shipmentOrder.getCustomerEnterpriseNumber(),
                shipmentOrder.getVesselNumber(),
                shipmentOrder.getArrivalDate(),
                shipmentOrder.getDepartureDate(),
                shipmentOrder.getBunkeringOperation().getOperationDate(),
                shipmentOrder.getInspectionOperation().getInspectionDate(),
                shipmentOrder.getInspectionOperation().getInspectorSignature(),
                shipmentOrder.getMatchedWithPO(),
                shipmentOrder.getShipmentStatus().name()
        );
    }

    public static ShipmentOrder toShipmentOrderEntity(ShipmentOrderJpaEntity shipmentOrderJpaEntity) {

        IO io = new IO(shipmentOrderJpaEntity.getInspectorSignature(), shipmentOrderJpaEntity.getInspectionOperationDate());
        BO bo = new BO(shipmentOrderJpaEntity.getBunkeringOperationDate());
        List<ShipmentOrderLineJpaEntity> orderLines = shipmentOrderJpaEntity.getShipmentOrderLines() == null ? new ArrayList<>() :
                shipmentOrderJpaEntity.getShipmentOrderLines();

        return new ShipmentOrder(
                shipmentOrderJpaEntity.getPoReferenceNumber(),
                orderLines.stream().map(ol ->
                        new OrderLine(
                                MaterialType.valueOf(ol.getMaterialType()),
                                ol.getAmount(),
                                UOM.valueOf(ol.getUom())
                        )).toList(),
                shipmentOrderJpaEntity.getCustomerEnterpriseNumber(),
                shipmentOrderJpaEntity.getVesselNumber(),
                shipmentOrderJpaEntity.getArrivalDate(),
                shipmentOrderJpaEntity.getDepartureDate(),
                io,
                bo,
                shipmentOrderJpaEntity.getIsMatchedWithPO(),
                ShipmentOrder.ShipmentStatus.valueOf(shipmentOrderJpaEntity.getShipmentStatus())
        );
    }
}
