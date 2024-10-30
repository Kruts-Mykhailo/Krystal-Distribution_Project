package be.kdg.prog6.domain;

import java.time.LocalDateTime;

public final class PayloadDelivery extends PayloadActivity {
    public PayloadDelivery(Double amount, LocalDateTime eventDateTime) {
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
