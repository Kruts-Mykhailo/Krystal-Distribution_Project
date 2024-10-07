package be.kdg.prog6.port.in;

import java.util.UUID;


public interface WarehouseInfoProjector {
    void project(UUID warehouseId, Double value);
}
