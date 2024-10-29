package be.kdg.prog6.adapters.in.messaging;

import be.kdg.prog6.domain.PONumber;
import be.kdg.prog6.events.ChangePOStatusEvent;
import be.kdg.prog6.ports.out.SendMatchingEventPort;
import be.kdg.prog6.ports.out.SendShipmentOrderFulfilledPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class WarehousePublisher implements SendShipmentOrderFulfilledPort, SendMatchingEventPort {

    private final RabbitTemplate rabbitTemplate;

    public WarehousePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void deductMaterialFromWarehouse(PONumber poNumber) {
        String routingKey = "status.%s.fulfilled".formatted(poNumber);
        this.rabbitTemplate.convertAndSend(
                MQTopology.CHANGE_ORDER_STATUS_EXCHANGE,
                routingKey,
                new ChangePOStatusEvent(poNumber.number())
        );
    }

    @Override
    public void sendMatchingEvent(PONumber poNumber) {
        String routingKey = "status.%s.matched".formatted(poNumber);
        this.rabbitTemplate.convertAndSend(
                MQTopology.CHANGE_ORDER_STATUS_EXCHANGE,
                routingKey,
                new ChangePOStatusEvent(poNumber.number())
        );
    }
}
