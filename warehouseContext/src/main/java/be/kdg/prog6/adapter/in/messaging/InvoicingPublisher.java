package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.events.POFulfilledEvent;
import be.kdg.prog6.events.PayloadArrivedEvent;
import be.kdg.prog6.port.out.PurchaseOrderFulfilledPort;
import be.kdg.prog6.port.out.StorageUpdatedPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InvoicingPublisher implements PurchaseOrderFulfilledPort, StorageUpdatedPort {
    private final RabbitTemplate rabbitTemplate;


    public InvoicingPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendInfoForCommission(POFulfilledEvent event) {
        String routingKey = "commission.%s.initiate".formatted(UUID.randomUUID());
        this.rabbitTemplate.convertAndSend(
                MQTopology.INVOICING_EXCHANGE,
                routingKey,
                event
        );
    }

    @Override
    public void sendPayloadDeliveryInfo(PayloadArrivedEvent event) {
        String routingKey = "payload.%s.delivered".formatted(UUID.randomUUID());
        this.rabbitTemplate.convertAndSend(
                MQTopology.INVOICING_EXCHANGE,
                routingKey,
                event);
    }
}
