package be.kdg.prog6.core;

import be.kdg.prog6.domain.OperationType;
import be.kdg.prog6.domain.WarehouseInfo;
import be.kdg.prog6.domain.WarehouseNumber;
import be.kdg.prog6.port.in.WarehouseInfoProjector;
import be.kdg.prog6.port.out.WarehouseProjectionFoundPort;
import be.kdg.prog6.port.out.WarehouseProjectionUpdatedPort;
import org.springframework.stereotype.Service;

@Service
public class WarehouseInfoProjectionImpl implements WarehouseInfoProjector {

    private final WarehouseProjectionFoundPort warehouseProjectionFoundPort;
    private final WarehouseProjectionUpdatedPort warehouseProjectionUpdatedPort;

    public WarehouseInfoProjectionImpl(WarehouseProjectionFoundPort warehouseProjectionFoundPort, WarehouseProjectionUpdatedPort warehouseProjectionUpdatedPort) {
        this.warehouseProjectionFoundPort = warehouseProjectionFoundPort;
        this.warehouseProjectionUpdatedPort = warehouseProjectionUpdatedPort;
    }

    @Override
    public void project(WarehouseNumber warehouseNumber, Double value, OperationType operationType) {
        WarehouseInfo warehouseInfo = warehouseProjectionFoundPort.getWarehouseByNumber(warehouseNumber);
        warehouseInfo.updateCapacity(value, operationType);
        warehouseProjectionUpdatedPort.updateCapacity(warehouseInfo);
    }
}
