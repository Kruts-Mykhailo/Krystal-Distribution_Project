package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.WarehouseInfo;

public interface WarehouseProjectionUpdatedPort {
    void update(WarehouseInfo warehouseInfo);
}
