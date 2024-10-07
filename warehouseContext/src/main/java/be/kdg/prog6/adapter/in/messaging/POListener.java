package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.domain.*;
import be.kdg.prog6.events.PurchaseOrderReceivedEvent;
import be.kdg.prog6.port.in.ReceivePOUseCase;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class POListener {

    private final ReceivePOUseCase receivePOUseCase;
    private final String PURCHASE_ORDER_QUEUE = "purchase_order_queue";

    public POListener(ReceivePOUseCase receivePOUseCase) {
        this.receivePOUseCase = receivePOUseCase;
    }

    @RabbitListener(queues = PURCHASE_ORDER_QUEUE, messageConverter = "jackson2jsonConverter")
    public void receivePurchaseOrder(PurchaseOrderReceivedEvent receivedEvent) {
        PurchaseOrder purchaseOrder = PurchaseOrder.fromEvent(receivedEvent);
        receivePOUseCase.receivePO(purchaseOrder);
    }
}
