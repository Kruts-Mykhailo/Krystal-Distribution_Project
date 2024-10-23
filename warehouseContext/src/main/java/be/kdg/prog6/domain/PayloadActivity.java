package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.Objects;

public abstract class PayloadActivity {
    private Double amount;
    private final LocalDateTime eventDateTime;
    private final ActivityType activityType;

    public PayloadActivity(LocalDateTime eventDateTime, ActivityType activityType, Double amount){
        this.eventDateTime = eventDateTime;
        this.activityType = activityType;
        this.amount = amount;

        Objects.requireNonNull(amount);
        if (amount < 0) {
            throw new IllegalArgumentException("Value should not be less than 0");
        }
    }

    public abstract Double payload();

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getAmount() {
        return amount;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public ActivityType getActivityType() {
        return activityType;
    }

}
