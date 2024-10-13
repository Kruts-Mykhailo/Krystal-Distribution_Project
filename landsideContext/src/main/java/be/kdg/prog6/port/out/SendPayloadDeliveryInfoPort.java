package be.kdg.prog6.port.out;

import be.kdg.prog6.events.StorageChangedEvent;

public interface SendPayloadDeliveryInfoPort {
    void sendPayloadDeliveryInfo(StorageChangedEvent event);
}
