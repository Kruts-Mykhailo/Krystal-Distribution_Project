package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.ActivityType;
import be.kdg.prog6.domain.PurchaseOrder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface PayloadRecordSavedPort {
    void savePayloadRecord(PayloadCommand payloadCommand);
    void saveMultiplePayloadRecords(List<PayloadCommand> payloadCommands);
}
