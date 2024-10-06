package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.events.PDTReceivedEvent;
import be.kdg.prog6.port.in.AdjustWarehouseInventoryUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LandsideListener {

    private final AdjustWarehouseInventoryUseCase adjustWarehouseInventoryUseCase;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public LandsideListener(AdjustWarehouseInventoryUseCase adjustWarehouseInventoryUseCase) {
        this.adjustWarehouseInventoryUseCase = adjustWarehouseInventoryUseCase;
    }

    @RabbitListener(queues = MQTopology.PAYLOAD_DELIVERY_TICKET_QUEUE, messageConverter = "jackson2JsonMessageConverter")
    public void payloadDelivered(PDTReceivedEvent pdtReceivedEvent) {
        logger.info(
                "Payload %s delivered to warehouse %s"
                        .formatted(
                            pdtReceivedEvent.materialType(),
                            pdtReceivedEvent.warehouseId()
                )
        );
        adjustWarehouseInventoryUseCase.savePayloadRecord(
                pdtReceivedEvent.warehouseId(),
                pdtReceivedEvent.sendTime(),
                pdtReceivedEvent.netWeight()
        );
    }
}
