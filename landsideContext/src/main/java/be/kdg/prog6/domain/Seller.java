package be.kdg.prog6.domain;


import java.util.UUID;

public class Seller {

    private SellerId sellerId;
    private String firstName;
    private String lastName;

    public record SellerId (UUID id) {}

}
