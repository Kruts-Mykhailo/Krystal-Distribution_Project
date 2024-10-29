package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.domain.OperationType;
import be.kdg.prog6.domain.WarehouseNumber;
import be.kdg.prog6.events.WarehouseCapacityChangeEvent;
import be.kdg.prog6.port.in.WarehouseInfoProjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class WarehouseListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(WarehouseListener.class);
    private final WarehouseInfoProjector warehouseInfoProjector;
    public static final String WAREHOUSE_FULLNESS_QUEUE = "warehouse_fullness_queue";

    public WarehouseListener(WarehouseInfoProjector warehouseInfoProjector) {
        this.warehouseInfoProjector = warehouseInfoProjector;
    }


    @RabbitListener(queues = WAREHOUSE_FULLNESS_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void warehouseUpdatedListener(WarehouseCapacityChangeEvent warehouseCapacityChangeEvent) {
        LOGGER.info("Warehouse {} updated", warehouseCapacityChangeEvent.number());
        warehouseInfoProjector.project(
                new WarehouseNumber(warehouseCapacityChangeEvent.number()),
                warehouseCapacityChangeEvent.initialCapacity(),
                OperationType.valueOf(warehouseCapacityChangeEvent.operationType()));

    }
}
