package be.kdg.prog6.port.in;

import java.time.LocalDateTime;
import java.util.UUID;

public interface AdjustWarehouseInventoryUseCase {
    void savePayloadRecord(UUID warehouseId, LocalDateTime sendTime, Double netWeight);
}
