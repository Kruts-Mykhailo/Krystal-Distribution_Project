package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.WBT;

public interface LeaveWeighingBridgeUseCase {
    WBT leaveWeighingBridge(PassBridgeCommand passBridgeCommand);
}
