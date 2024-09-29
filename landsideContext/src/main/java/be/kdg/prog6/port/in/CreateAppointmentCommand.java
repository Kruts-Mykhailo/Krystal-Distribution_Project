package be.kdg.prog6.port.in;


import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;

import java.time.LocalDate;

public record CreateAppointmentCommand(MaterialType materialType, LicensePlate licensePlate, Seller.SellerId sellerId, LocalDate scheduleDate) {
    public CreateAppointmentCommand {
        if (scheduleDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Schedule date cannot be in the past.");
        }
    }
}
