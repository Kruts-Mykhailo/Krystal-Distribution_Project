package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.events.WarehouseUpdatedEvent;
import be.kdg.prog6.port.in.WarehouseInfoProjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class WarehouseListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseListener.class);
    private final WarehouseInfoProjector warehouseInfoProjector;

    public WarehouseListener(WarehouseInfoProjector warehouseInfoProjector) {
        this.warehouseInfoProjector = warehouseInfoProjector;
    }


    @RabbitListener(queues = MQTopology.WAREHOUSE_FULLNESS_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void warehouseUpdatedListener(WarehouseUpdatedEvent warehouseUpdatedEvent) {
        LOGGER.info("Warehouse {} has capacity of: {}",
                warehouseUpdatedEvent.warehouseId(),
                warehouseUpdatedEvent.value());
        warehouseInfoProjector.project(warehouseUpdatedEvent.warehouseId(), warehouseUpdatedEvent.value());

    }
}
