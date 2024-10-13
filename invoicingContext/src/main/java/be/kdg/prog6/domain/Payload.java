package be.kdg.prog6.domain;

import java.time.LocalDateTime;

public class Payload {
    private Double tons;
    private final LocalDateTime deliveryDate;

    public Payload(LocalDateTime deliveryDate, Double tons) {
        this.deliveryDate = deliveryDate;
        this.tons = tons;
    }


    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public Double getTons() {
        return tons;
    }

    public void setTons(Double tons) {
        this.tons = tons;
    }

}
