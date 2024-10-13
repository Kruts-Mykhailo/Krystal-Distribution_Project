package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.events.StorageChangedEvent;
import be.kdg.prog6.port.out.SendPayloadDeliveryInfoPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class InvoicingPublisher implements SendPayloadDeliveryInfoPort {

    private final RabbitTemplate rabbitTemplate;

    public InvoicingPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendPayloadDeliveryInfo(StorageChangedEvent event) {
        String routingKey = "landside.%s.payload.delivery".formatted(event.sellerId());
        this.rabbitTemplate.convertAndSend(
                MQTopology.PAYLOAD_DELIVERY_TICKET_EXCHANGE,
                routingKey,
                event);
    }
}
