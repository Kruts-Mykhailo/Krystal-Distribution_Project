package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.domain.ShippingOrder;
import be.kdg.prog6.events.ShipOperationsCompletedEvent;
import be.kdg.prog6.port.in.ShipOperationsFinishedUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WatersideListener {

    private final ShipOperationsFinishedUseCase shipOperationsFinishedUseCase;
    private final String FINISH_OPERATIONS_QUEUE = "finish_operations_queue";
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public WatersideListener(ShipOperationsFinishedUseCase shipOperationsFinishedUseCase) {
        this.shipOperationsFinishedUseCase = shipOperationsFinishedUseCase;
    }

    @RabbitListener(queues = FINISH_OPERATIONS_QUEUE, messageConverter = "jackson2JsonMessageConverter")
    public void operationsFinished(ShipOperationsCompletedEvent event) {
        logger.info("Initiate deducting of payload for PO: %s".formatted(event.poNumber()));
        shipOperationsFinishedUseCase.initiateLoading(new ShippingOrder(event.poNumber()));

    }
}
