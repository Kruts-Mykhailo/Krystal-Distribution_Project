package be.kdg.prog6.port.in;

import be.kdg.prog6.events.StorageChangedEvent;

public interface ReceivePayloadDeliveryUseCase {
    void addPayload(StorageChangedEvent storageChangedEvent);
}
