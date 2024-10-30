package be.kdg.prog6.domain;


import java.util.Objects;
import java.util.UUID;

public class Seller {

    private SellerId sellerId;
    private String name;

    public record SellerId (UUID id) {
        public SellerId {
            Objects.requireNonNull(id);
        }
    }

    public Seller(SellerId sellerId, String name) {
        this.sellerId = sellerId;
        this.name = name;
    }

    public Seller(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
