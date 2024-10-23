package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public final class PayloadDeliveryEvent extends PayloadActivity {
    public PayloadDeliveryEvent(Double amount, LocalDateTime eventDateTime) {
        super(eventDateTime, ActivityType.DELIVERY, amount);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Override
    public Double payload() {
        return this.getAmount();
    }
}
