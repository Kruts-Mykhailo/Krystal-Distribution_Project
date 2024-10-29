package be.kdg.prog6.adapters.out.db.shipmentOrder;
import be.kdg.prog6.domain.*;

public class ShipmentOrderConverter {

    public static ShipmentOrderJpaEntity toShipmentOrderJpaEntity(ShipmentOrder shipmentOrder) {
        return new ShipmentOrderJpaEntity(
                shipmentOrder.getPoReferenceNumber().number(),
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
        return new ShipmentOrder(
                new PONumber(shipmentOrderJpaEntity.getPoReferenceNumber()),
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
