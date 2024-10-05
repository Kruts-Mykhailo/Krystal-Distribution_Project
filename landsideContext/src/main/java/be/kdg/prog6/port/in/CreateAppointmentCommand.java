package be.kdg.prog6.port.in;


import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import java.time.LocalDateTime;
import java.util.Objects;

public record CreateAppointmentCommand(MaterialType materialType, LicensePlate licensePlate, Seller.SellerId sellerId, LocalDateTime scheduleDateTime) {
    public CreateAppointmentCommand {
        if (scheduleDateTime.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("Schedule date cannot be in the past.");
        }
        if (scheduleDateTime.getMinute() != 0 || scheduleDateTime.getSecond() != 0 || scheduleDateTime.getNano() != 0) {
            throw new IllegalArgumentException("Schedule date must contain only date and hour.");
        }
        if (sellerId == null) {
            throw new IllegalArgumentException("SellerId must not be null.");
        }

    }
}
