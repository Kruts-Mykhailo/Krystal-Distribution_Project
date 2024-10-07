package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.ActivityType;

import java.time.LocalDateTime;
import java.util.UUID;

public interface PayloadRecordSaved {
    void savePayloadRecord(ActivityType activityType, Double amount, UUID warehouseId, LocalDateTime timestamp);
}
