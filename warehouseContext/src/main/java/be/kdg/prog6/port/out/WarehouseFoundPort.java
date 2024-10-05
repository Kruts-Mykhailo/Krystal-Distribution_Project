package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.Warehouse;

import java.util.UUID;

public interface WarehouseFoundPort {
    Warehouse getWarehouseById(UUID uuid);
}
