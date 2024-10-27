package be.kdg.prog6.core;

import be.kdg.prog6.adapters.exceptions.VesselAlreadyLeftException;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.CheckIfVesselCanLeaveUseCase;
import be.kdg.prog6.ports.out.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CheckIfVesselCanLeaveUseCaseImpl implements CheckIfVesselCanLeaveUseCase {

    private final FindSOPort findSOPort;

    public CheckIfVesselCanLeaveUseCaseImpl(FindSOPort findSOPort) {
        this.findSOPort = findSOPort;
    }

    @Override
    @Transactional
    public ShipmentOrder checkIfVesselCanLeave(String vesselNumber) {
        ShipmentOrder shipmentOrder = findSOPort.findShipmentOrderByVesselNumber(vesselNumber);
        if (shipmentOrder.getShipmentStatus() == ShipmentOrder.ShipmentStatus.LEFT_PORT) {
            throw new VesselAlreadyLeftException("Vessel %s already left the site".formatted(vesselNumber));
        }
        return shipmentOrder;
    }
}
