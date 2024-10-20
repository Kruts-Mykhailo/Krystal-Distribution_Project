package be.kdg.prog6.domain;

public record LicensePlate(String licensePlate) {
    public LicensePlate {
        if (licensePlate.isEmpty()) {
            throw new IllegalArgumentException("License plate is empty");
        }
    }
}
