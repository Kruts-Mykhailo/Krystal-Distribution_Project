package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.Warehouse;

public interface WarehouseUpdatedPort {
    void updateSnapshot(Warehouse warehouse);
}
