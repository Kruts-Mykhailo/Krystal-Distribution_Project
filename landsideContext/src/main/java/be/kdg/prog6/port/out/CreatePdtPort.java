package be.kdg.prog6.port.out;

import be.kdg.prog6.events.PayloadDeliveredEvent;


public interface CreatePdtPort {
    void sendPdt(PayloadDeliveredEvent payloadDeliveredEvent);
}
