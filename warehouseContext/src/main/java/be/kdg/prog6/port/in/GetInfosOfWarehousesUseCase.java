package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.Warehouse;

import java.util.List;

public interface GetInfosOfWarehousesUseCase {
    List<Warehouse> getInfosOfWarehouses();
}
