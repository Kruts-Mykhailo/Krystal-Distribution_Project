package be.kdg.prog6.adapters.in.messaging;

import be.kdg.prog6.events.CommissionEvent;
import be.kdg.prog6.events.ShipOperationsCompletedEvent;
import be.kdg.prog6.ports.out.SendCommissionInfoPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InvoicePublisher implements SendCommissionInfoPort {

    private final RabbitTemplate rabbitTemplate;

    public InvoicePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void sendInfoForCommission(CommissionEvent commissionEvent) {
        String routingKey = ".%s.commissions".formatted(UUID.randomUUID());
        this.rabbitTemplate.convertAndSend(
                MQTopology.FINISH_OPERATIONS_EXCHANGE,
                routingKey,
                commissionEvent
        );
    }
}
