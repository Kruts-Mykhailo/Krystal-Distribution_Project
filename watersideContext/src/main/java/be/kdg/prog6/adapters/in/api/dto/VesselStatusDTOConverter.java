package be.kdg.prog6.adapters.in.api.dto;

import be.kdg.prog6.domain.ShipmentOrder;

public class VesselStatusDTOConverter {
    public static VesselStatusDTO convert(ShipmentOrder shipmentOrder) {
        return new VesselStatusDTO(
                shipmentOrder.getVesselNumber(),
                shipmentOrder.getArrivalDate(),
                shipmentOrder.getShipmentStatus().name(),
                new BunkeringOperationDTO(shipmentOrder.getBunkeringOperation().getOperationDate()),
                new InspectionOperationDTO(
                        shipmentOrder.getInspectionOperation().getInspectionStatus().name(),
                        shipmentOrder.getInspectionOperation().getInspectionDate(),
                        shipmentOrder.getInspectionOperation().getInspectorSignature()
                ),
                shipmentOrder.getMatchedWithPO()

        );
    }
}
