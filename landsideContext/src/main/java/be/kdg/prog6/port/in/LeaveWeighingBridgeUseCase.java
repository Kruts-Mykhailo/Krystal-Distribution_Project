package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.WBT;

import java.util.Optional;

public interface LeaveWeighingBridgeUseCase {
    Optional<WBT> leaveWeighingBridge(PassBridgeCommand passBridgeCommand);
}
