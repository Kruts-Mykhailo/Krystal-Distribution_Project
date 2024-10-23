package be.kdg.prog6.domain;


import java.util.Objects;
import java.util.UUID;

public class Seller {

    public record SellerId (UUID id) {
        public SellerId {
            Objects.requireNonNull(id);
        }
    }

}
