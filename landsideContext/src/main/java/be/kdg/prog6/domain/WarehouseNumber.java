package be.kdg.prog6.domain;

public record WarehouseNumber(String number) {
    public WarehouseNumber {
        if (!number.startsWith("W")) {
            throw new IllegalArgumentException("Invalid warehouse number: " + number);
        }
    }
}
