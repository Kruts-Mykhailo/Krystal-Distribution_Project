package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.WarehouseNumber;

public interface RecordWarehouseStateUseCase {
    void snapshot(WarehouseNumber warehouseNumber);
}
