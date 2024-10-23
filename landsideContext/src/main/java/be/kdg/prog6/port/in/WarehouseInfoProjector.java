package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.OperationType;
import be.kdg.prog6.domain.WarehouseNumber;


public interface WarehouseInfoProjector {
    void project(WarehouseNumber warehouseNumber, Double value, OperationType operationType);
}
