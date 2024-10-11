package be.kdg.prog6.adapters.in.messaging;

import be.kdg.prog6.domain.PO;
import be.kdg.prog6.events.PurchaseOrderReceivedEvent;
import be.kdg.prog6.ports.in.ReceivePOUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class POListener {

    private final ReceivePOUseCase receivePOUseCase;
    private final String PURCHASE_ORDER_QUEUE = "purchase_order_queue";

    public POListener(ReceivePOUseCase receivePOUseCase) {
        this.receivePOUseCase = receivePOUseCase;
    }

    @RabbitListener(queues = PURCHASE_ORDER_QUEUE, messageConverter = "jackson2JsonMessageConverter")
    public void receivePurchaseOrder(PurchaseOrderReceivedEvent receivedEvent) {
        PO purchaseOrder = PO.fromEvent(receivedEvent);
        receivePOUseCase.receivePO(purchaseOrder);

    }
}
