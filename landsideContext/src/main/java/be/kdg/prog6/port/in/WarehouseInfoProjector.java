package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.OperationType;

import java.util.UUID;


public interface WarehouseInfoProjector {
    void project(UUID warehouseId, Double value, OperationType operationType);
}
