package be.kdg.prog6.adapter.in.messaging;

import be.kdg.prog6.events.CommissionEvent;
import be.kdg.prog6.port.in.CalculateCommissionFeeUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class WatersideListener {

    public static final String FINISH_OPERATIONS_QUEUE = "finish_operations_queue";
    private static final Logger log = LoggerFactory.getLogger(WatersideListener.class);
    private final CalculateCommissionFeeUseCase calculateCommissionFeeUseCase;

    public WatersideListener(CalculateCommissionFeeUseCase calculateCommissionFeeUseCase) {
        this.calculateCommissionFeeUseCase = calculateCommissionFeeUseCase;
    }

    @RabbitListener(queues = FINISH_OPERATIONS_QUEUE, messageConverter = "#{jackson2JsonMessageConverter}")
    public void purchaseOrderFulfilled(CommissionEvent commissionEvent) {
        log.info("Received commission event");
        calculateCommissionFeeUseCase.calculate(commissionEvent);
    }
}
