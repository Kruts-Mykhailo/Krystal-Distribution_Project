package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.events.CalculateCommissionForPurchaseOrderEvent;
import be.kdg.prog6.events.StorageChangeEvent;
import be.kdg.prog6.port.out.PurchaseOrderFulfilledPort;
import be.kdg.prog6.port.out.InvoicingStorageRecordUpdatedPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InvoicingPublisher implements PurchaseOrderFulfilledPort, InvoicingStorageRecordUpdatedPort {
    private final RabbitTemplate rabbitTemplate;


    public InvoicingPublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendInfoForCommission(CalculateCommissionForPurchaseOrderEvent event) {
        String routingKey = "commission.%s.initiate".formatted(UUID.randomUUID());
        this.rabbitTemplate.convertAndSend(
                MQTopology.INVOICING_EXCHANGE,
                routingKey,
                event
        );
    }

    @Override
    public void send(StorageChangeEvent event) {
        String routingKey = "payload.%s.delivered".formatted(UUID.randomUUID());
        this.rabbitTemplate.convertAndSend(
                MQTopology.INVOICING_EXCHANGE,
                routingKey,
                event);
    }
}
