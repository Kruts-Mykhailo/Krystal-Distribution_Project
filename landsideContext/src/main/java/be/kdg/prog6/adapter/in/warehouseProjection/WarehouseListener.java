package be.kdg.prog6.adapter.in.warehouseProjection;

import be.kdg.prog6.port.in.WarehouseInfoProjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class WarehouseListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseListener.class);
    private static final String CREATED_ACTIVITIES_QUEUE = "created_activities";
    private final WarehouseInfoProjector warehouseInfoProjector;

    public WarehouseListener(WarehouseInfoProjector warehouseInfoProjector) {
        this.warehouseInfoProjector = warehouseInfoProjector;
    }


    @RabbitListener(queues = CREATED_ACTIVITIES_QUEUE, messageConverter = "#{consumerJackson2MessageConverter}")
    public void warehouseUpdatedListener(WarehouseUpdatedEvent warehouseUpdatedEvent) {
        LOGGER.info("Warehouse {} is full: {}",
                warehouseUpdatedEvent.warehouseId(),
                warehouseUpdatedEvent.fullCapacity());
        warehouseInfoProjector.project(warehouseUpdatedEvent.warehouseId(), warehouseUpdatedEvent.fullCapacity());

    }
}
