package be.kdg.prog6.port.in;

import be.kdg.prog6.events.StorageChangeEvent;

public interface ReceivePayloadDeliveryUseCase {
    void addPayload(StorageChangeEvent storageChangeEvent);
}
