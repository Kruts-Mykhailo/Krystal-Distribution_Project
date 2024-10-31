package be.kdg.prog6.port.in;

import be.kdg.prog6.events.AdjustInventoryEvent;

public interface AdjustWarehouseInventoryUseCase {
    void savePayloadRecord(AdjustInventoryEvent command);
}
