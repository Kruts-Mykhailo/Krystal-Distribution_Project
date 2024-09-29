package be.kdg.prog6.domain;


import java.util.UUID;

public class Seller {

    private SellerId sellerId;
    private String firstName;
    private String lastName;

    public record SellerId (UUID id) {}

    public SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(SellerId sellerId) {
        this.sellerId = sellerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
