package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.domain.ActivityType;
import be.kdg.prog6.domain.WarehouseNumber;
import be.kdg.prog6.events.WarehouseCapacityChangeEvent;
import be.kdg.prog6.port.out.ProjectWarehouseInfoPort;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class LandsidePublisher implements ProjectWarehouseInfoPort {

    private final RabbitTemplate rabbitTemplate;

    public LandsidePublisher(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void projectWarehouseCapacity(WarehouseCapacityChangeEvent event) {
        String routingKey = String.format("warehouse.%s.capacity.changed", UUID.randomUUID());
        this.rabbitTemplate.convertAndSend(
                MQTopology.WAREHOUSE_FULLNESS_EXCHANGE,
                routingKey,
                event
        );
    }
}
