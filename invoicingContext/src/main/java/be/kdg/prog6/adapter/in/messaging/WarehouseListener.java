package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.events.CommissionEvent;
import be.kdg.prog6.events.StorageChangeEvent;
import be.kdg.prog6.port.in.CalculateCommissionFeeUseCase;
import be.kdg.prog6.port.in.ReceivePayloadDeliveryUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WarehouseListener {

    private static final Logger log = LoggerFactory.getLogger(WarehouseListener.class);
    public static final String COMMISSIONS_QUEUE = "commissions_queue";
    public static final String PAYLOAD_DELIVERY_QUEUE = "payload_delivery_queue";
    private final CalculateCommissionFeeUseCase calculateCommissionFeeUseCase;
    private final ReceivePayloadDeliveryUseCase receivePayloadDeliveryUseCase;

    public WarehouseListener(CalculateCommissionFeeUseCase calculateCommissionFeeUseCase, ReceivePayloadDeliveryUseCase receivePayloadDeliveryUseCase) {
        this.calculateCommissionFeeUseCase = calculateCommissionFeeUseCase;
        this.receivePayloadDeliveryUseCase = receivePayloadDeliveryUseCase;
    }

    @RabbitListener(queues = COMMISSIONS_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void purchaseOrderFulfilled(CommissionEvent commissionEvent) {
        log.info("Received commission event");
        calculateCommissionFeeUseCase.calculate(commissionEvent);
    }

    @RabbitListener(queues = PAYLOAD_DELIVERY_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void receivePayloadDeliveryInfo(StorageChangeEvent storageChangeEvent) {
        receivePayloadDeliveryUseCase.addPayload(storageChangeEvent);
    }
}
