package be.kdg.prog6.port.in;

public interface AdjustWarehouseInventoryUseCase {
    void savePayloadRecord(PayloadDeliveredCommand payloadDeliveredCommand);
}
