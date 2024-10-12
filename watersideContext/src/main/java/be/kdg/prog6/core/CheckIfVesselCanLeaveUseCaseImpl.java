package be.kdg.prog6.core;

import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.CheckIfVesselCanLeaveUseCase;
import be.kdg.prog6.ports.out.FindSOPort;
import be.kdg.prog6.ports.out.ShipmentOrderFulfilledPort;
import be.kdg.prog6.ports.out.UpdateSOPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CheckIfVesselCanLeaveUseCaseImpl implements CheckIfVesselCanLeaveUseCase {

    private final FindSOPort findSOPort;
    private final UpdateSOPort updateSOPort;
    private final ShipmentOrderFulfilledPort shipmentOrderFulfilledPort;

    public CheckIfVesselCanLeaveUseCaseImpl(FindSOPort findSOPort, UpdateSOPort updateSOPort, ShipmentOrderFulfilledPort shipmentOrderFulfilledPort) {
        this.findSOPort = findSOPort;
        this.updateSOPort = updateSOPort;
        this.shipmentOrderFulfilledPort = shipmentOrderFulfilledPort;
    }

    @Override
    @Transactional
    public ShipmentOrder checkIfVesselCanLeave(String vesselNumber) {

        ShipmentOrder shipmentOrder = findSOPort.findShipmentOrderByVesselNumber(vesselNumber);
        if (shipmentOrder.canVesselLeave()) {
            shipmentOrder.leave();
            shipmentOrderFulfilledPort.deductMaterialFromWarehouse(shipmentOrder.getPoReferenceNumber());
            shipmentOrder = updateSOPort.updateShipmentOrder(shipmentOrder);

        }

        return shipmentOrder;
    }
}
