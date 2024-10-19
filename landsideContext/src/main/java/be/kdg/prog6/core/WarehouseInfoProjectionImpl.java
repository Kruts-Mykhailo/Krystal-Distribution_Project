package be.kdg.prog6.core;

import be.kdg.prog6.domain.OperationType;
import be.kdg.prog6.domain.WarehouseInfo;
import be.kdg.prog6.port.in.WarehouseInfoProjector;
import be.kdg.prog6.port.out.WarehouseProjectionFoundPort;
import be.kdg.prog6.port.out.WarehouseProjectionUpdatedPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WarehouseInfoProjectionImpl implements WarehouseInfoProjector {

    private final WarehouseProjectionFoundPort warehouseProjectionFoundPort;
    private final WarehouseProjectionUpdatedPort warehouseProjectionUpdatedPort;

    public WarehouseInfoProjectionImpl(WarehouseProjectionFoundPort warehouseProjectionFoundPort, WarehouseProjectionUpdatedPort warehouseProjectionUpdatedPort) {
        this.warehouseProjectionFoundPort = warehouseProjectionFoundPort;
        this.warehouseProjectionUpdatedPort = warehouseProjectionUpdatedPort;
    }

    @Override
    public void project(UUID warehouseId, Double value, OperationType operationType) {
        WarehouseInfo warehouseInfo = warehouseProjectionFoundPort.getWarehouseById(warehouseId);
        warehouseInfo.updateCapacity(value, operationType);
        warehouseProjectionUpdatedPort.update(warehouseInfo);
    }
}
