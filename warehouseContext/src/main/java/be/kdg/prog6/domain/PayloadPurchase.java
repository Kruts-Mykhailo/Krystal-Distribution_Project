package be.kdg.prog6.domain;

import java.time.LocalDateTime;

public final class PayloadPurchase extends PayloadActivity {

    public PayloadPurchase(Double amount, LocalDateTime eventDateTime) {
        super(eventDateTime, ActivityType.PURCHASE, amount);

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
        return -Math.abs(this.getAmount());
    }
}
