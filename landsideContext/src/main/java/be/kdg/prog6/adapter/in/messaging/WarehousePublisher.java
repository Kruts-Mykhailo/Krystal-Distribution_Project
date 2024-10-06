package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.events.PayloadDeliveredEvent;
import be.kdg.prog6.port.out.CreatePdtPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import java.util.UUID;

@Component
public class WarehousePublisher implements CreatePdtPort {

    private final RabbitTemplate rabbitTemplate;

    public WarehousePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendPdt(PayloadDeliveredEvent pdt) {
        String routingKey = String.format("landside.%s.pdt.received", UUID.randomUUID());
        this.rabbitTemplate.convertAndSend(
                MQTopology.PAYLOAD_DELIVERY_TICKET_EXCHANGE,
                routingKey,
                pdt
        );
    }
}
