package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.domain.WarehouseNumber;

public interface GetWarehouseInfoUseCase {
    Warehouse getWarehouseInfo(WarehouseNumber warehouseNumber);
}
