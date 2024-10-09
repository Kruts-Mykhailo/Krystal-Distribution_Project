package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.domain.ShippingOrder;
import be.kdg.prog6.events.ReceiveMatchShippingOrderEvent;
import be.kdg.prog6.port.in.MatchShippingOrderUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WatersideListener {

    private final MatchShippingOrderUseCase matchShippingOrderUseCase;
    private final String MATCH_ORDERS_QUEUE = "match_orders_queue";
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public WatersideListener(MatchShippingOrderUseCase matchShippingOrderUseCase) {
        this.matchShippingOrderUseCase = matchShippingOrderUseCase;
    }

    @RabbitListener(queues = MATCH_ORDERS_QUEUE, messageConverter = "jackson2JsonMessageConverter")
    public void shippingOrderMatched(ReceiveMatchShippingOrderEvent event) {
        logger.info("Initiate loading of payload for PO: %s".formatted(event.poNumber()));
        matchShippingOrderUseCase.initiateLoading(new ShippingOrder(event.poNumber()));

    }
}
