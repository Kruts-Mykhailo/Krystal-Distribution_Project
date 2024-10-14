package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.domain.ShippingOrder;
import be.kdg.prog6.events.ChangePOStatusEvent;
import be.kdg.prog6.port.in.MatchPOAndSOUseCase;
import be.kdg.prog6.port.in.PurchaseOrderFulfilledUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WatersideListener {

    private final PurchaseOrderFulfilledUseCase purchaseOrderFulfilledUseCase;
    private final MatchPOAndSOUseCase matchPOAndSOUseCase;
    public static final String FUlFILL_ORDER_STATUS_QUEUE = "full_order_status_queue";
    public static final String MATCH_ORDER_STATUS_QUEUE = "match_order_status_queue";
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public WatersideListener(PurchaseOrderFulfilledUseCase purchaseOrderFulfilledUseCase, MatchPOAndSOUseCase matchPOAndSOUseCase) {
        this.purchaseOrderFulfilledUseCase = purchaseOrderFulfilledUseCase;
        this.matchPOAndSOUseCase = matchPOAndSOUseCase;
    }

    @RabbitListener(queues = FUlFILL_ORDER_STATUS_QUEUE, messageConverter = "jackson2JsonMessageConverter")
    public void fulfillPurchaseOrder(ChangePOStatusEvent event) {
        logger.info("Initiate deducting of payload for PO: %s".formatted(event.poNumber()));
        purchaseOrderFulfilledUseCase.deductMaterial(new ShippingOrder(event.poNumber()));

    }

    @RabbitListener(queues = MATCH_ORDER_STATUS_QUEUE, messageConverter = "jackson2JsonMessageConverter")
    public void matchPOAndSO(ChangePOStatusEvent event) {
        logger.info("Initiate matching of payload for PO: %s".formatted(event.poNumber()));
        matchPOAndSOUseCase.matchOrders(new ShippingOrder(event.poNumber()));
    }
}
