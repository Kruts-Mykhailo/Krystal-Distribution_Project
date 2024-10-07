package be.kdg.prog6.core;

import be.kdg.prog6.port.in.WarehouseInfoProjector;
import be.kdg.prog6.port.out.WarehouseInfoPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WarehouseInfoProjectionImpl implements WarehouseInfoProjector {

    private final WarehouseInfoPort warehouseInfoPort;

    public WarehouseInfoProjectionImpl(WarehouseInfoPort warehouseInfoPort) {
        this.warehouseInfoPort = warehouseInfoPort;
    }

    @Override
    public void project(UUID warehouseId, Double value) {
        warehouseInfoPort.updateWarehouse(warehouseId, value);
    }
}
