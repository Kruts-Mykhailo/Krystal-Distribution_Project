package be.kdg.prog6.port.out;

import java.util.List;

public interface PayloadRecordSavedPort {
    void saveOrUpdatePayloadRecord(PayloadCommand payloadCommand);
    void saveMultiplePayloadRecords(List<PayloadCommand> payloadCommands);
}
