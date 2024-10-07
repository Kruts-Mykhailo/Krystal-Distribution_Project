package be.kdg.prog6.port.out;

import java.util.UUID;

public interface ProjectWarehouseInfoPort {
    void projectWarehouseCapacity(UUID warehouseId, Double capacity);
}
