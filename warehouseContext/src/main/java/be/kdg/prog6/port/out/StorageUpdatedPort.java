package be.kdg.prog6.port.out;

import be.kdg.prog6.events.PayloadArrivedEvent;

public interface StorageUpdatedPort {
    void sendPayloadDeliveryInfo(PayloadArrivedEvent event);
}
