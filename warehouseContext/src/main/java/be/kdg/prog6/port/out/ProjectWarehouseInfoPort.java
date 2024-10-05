package be.kdg.prog6.port.out;

import java.util.UUID;

public interface ProjectWarehouseInfoPort {
    void updateWarehouseCapacity(UUID warehouseId, Boolean capacity);
}
