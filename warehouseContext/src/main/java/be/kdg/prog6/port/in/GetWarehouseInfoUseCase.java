package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.domain.WarehouseInfo;

public interface GetWarehouseInfoUseCase {
    WarehouseInfo getWarehouseInfo(int warehouseNumber);
}
