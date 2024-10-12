package be.kdg.prog6.adapters.in.api.dto;

import be.kdg.prog6.domain.ShipmentOrder;

public class VesselArrivalDTOConverter {
    public static VesselArrivalDTO convert(ShipmentOrder shipmentOrder) {
        return new VesselArrivalDTO(
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
