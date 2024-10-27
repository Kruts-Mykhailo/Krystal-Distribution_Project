package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.domain.*;
import be.kdg.prog6.events.PurchaseOrderCreatedEvent;
import be.kdg.prog6.port.in.ReceivePOUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@Component
public class POListener {

    private final ReceivePOUseCase receivePOUseCase;
    private final String PURCHASE_ORDER_QUEUE = "purchase_order_queue";
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    public POListener(ReceivePOUseCase receivePOUseCase) {
        this.receivePOUseCase = receivePOUseCase;
    }

    @RabbitListener(queues = PURCHASE_ORDER_QUEUE, messageConverter = "jackson2JsonMessageConverter")
    public void receivePurchaseOrder(PurchaseOrderCreatedEvent receivedEvent) {
        PurchaseOrder purchaseOrder = new PurchaseOrder(
                new Seller.SellerId(receivedEvent.getPurchaseOrder().getSellerParty().getUuid()),
                receivedEvent.getPurchaseOrder().getOrderLines()
                        .stream()
                        .map(ol -> new OrderLine(
                                MaterialType.fromCode(ol.getMaterialType()),
                                (double) ol.getQuantity(),
                                UOM.fromCode(ol.getUom())
                        ))
                        .collect(Collectors.toList()),
                new PONumber(receivedEvent.getPurchaseOrder().getPoNumber()),
                PurchaseOrder.OrderStatus.OUTSTANDING,
                LocalDateTime.now()
        );
        logger.info("Received purchase order: {}", purchaseOrder);
        receivePOUseCase.receivePO(purchaseOrder);
    }
}
