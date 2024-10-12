package be.kdg.prog6.ports.in;

import be.kdg.prog6.domain.ShipmentOrder;

public interface CheckIfVesselCanLeaveUseCase {
    ShipmentOrder checkIfVesselCanLeave(String vesselNumber);
}
