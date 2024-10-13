package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.events.StorageChangedEvent;
import be.kdg.prog6.port.in.ReceivePayloadDeliveryUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LandsideListener {
    public static final String PAYLOAD_DELIVERY_QUEUE = "payload_delivery_queue";
    private final ReceivePayloadDeliveryUseCase receivePayloadDeliveryUseCase;

    public LandsideListener(ReceivePayloadDeliveryUseCase receivePayloadDeliveryUseCase) {
        this.receivePayloadDeliveryUseCase = receivePayloadDeliveryUseCase;
    }

    @RabbitListener(queues = PAYLOAD_DELIVERY_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receivePayloadDeliveryInfo(StorageChangedEvent storageChangedEvent) {
        receivePayloadDeliveryUseCase.addPayload(storageChangedEvent);
    }
}
