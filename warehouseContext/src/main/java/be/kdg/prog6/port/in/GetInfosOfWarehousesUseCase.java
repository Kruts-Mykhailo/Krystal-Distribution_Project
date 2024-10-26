package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.WarehouseInfo;

import java.util.List;

public interface GetInfosOfWarehousesUseCase {
    List<WarehouseInfo> getInfosOfWarehouses();
}
