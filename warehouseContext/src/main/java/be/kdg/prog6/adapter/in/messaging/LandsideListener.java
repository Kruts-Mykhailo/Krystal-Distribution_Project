package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.domain.WarehouseNumber;
import be.kdg.prog6.events.PayloadDeliveredEvent;
import be.kdg.prog6.events.AdjustInventoryEvent;
import be.kdg.prog6.port.in.AdjustWarehouseInventoryUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class LandsideListener {

    public static final String PAYLOAD_DELIVERY_TICKET_QUEUE = "payload_delivery_ticket_queue";
    private final AdjustWarehouseInventoryUseCase adjustWarehouseInventoryUseCase;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public LandsideListener(AdjustWarehouseInventoryUseCase adjustWarehouseInventoryUseCase) {
        this.adjustWarehouseInventoryUseCase = adjustWarehouseInventoryUseCase;
    }

    @RabbitListener(queues = PAYLOAD_DELIVERY_TICKET_QUEUE, messageConverter = "jackson2JsonMessageConverter")
    public void payloadDelivered(PayloadDeliveredEvent pdtReceivedEvent) {
        logger.info(
                "Payload of %s delivered to warehouse %s"
                        .formatted(
                            pdtReceivedEvent.materialType(),
                            pdtReceivedEvent.warehouseNumber()
                )
        );
        adjustWarehouseInventoryUseCase.savePayloadRecord(
                new AdjustInventoryEvent(
                        new WarehouseNumber(pdtReceivedEvent.warehouseNumber()),
                        pdtReceivedEvent.sendTime(),
                        pdtReceivedEvent.netWeight()));
    }
}
