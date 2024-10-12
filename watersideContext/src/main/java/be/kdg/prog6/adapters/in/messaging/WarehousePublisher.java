package be.kdg.prog6.adapters.in.messaging;

import be.kdg.prog6.events.ShipOperationsCompletedEvent;
import be.kdg.prog6.ports.out.ShipmentOrderFulfilledPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class WarehousePublisher implements ShipmentOrderFulfilledPort {

    private final RabbitTemplate rabbitTemplate;

    public WarehousePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void deductMaterialFromWarehouse(String poNumber) {
        String routingKey = "operations.%s.finished".formatted(poNumber);
        this.rabbitTemplate.convertAndSend(
                MQTopology.FINISH_OPERATIONS_EXCHANGE,
                routingKey,
                new ShipOperationsCompletedEvent(poNumber)
        );
    }
}
