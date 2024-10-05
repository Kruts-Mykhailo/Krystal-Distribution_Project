package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.PDT;
import org.springframework.messaging.handler.annotation.Payload;

import java.time.LocalDateTime;
import java.util.UUID;

public record PayloadDeliveredCommand (UUID warehouseId, LocalDateTime sendTime, Double netWeight){

    public static PayloadDeliveredCommand fromPDT(PDT pdt) {
        return new PayloadDeliveredCommand(pdt.warehouseId(), pdt.sendTime(), pdt.netWeight());
    }
}
