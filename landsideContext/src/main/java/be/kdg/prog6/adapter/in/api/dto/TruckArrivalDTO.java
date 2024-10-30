package be.kdg.prog6.adapter.in.api.dto;

import be.kdg.prog6.domain.Appointment;

import java.time.LocalDateTime;

public record TruckArrivalDTO(String licensePlate, LocalDateTime windowStart, LocalDateTime windowEnd, String arrivalStatus, String sellerName){
    public static TruckArrivalDTO from(Appointment appointment) {
        return new TruckArrivalDTO(
                appointment.getTruckLicensePlate().licensePlate(),
                appointment.getWindowStartTime(),
                appointment.getWindowEndTime(),
                appointment.getTruckArrivalStatus().name(),
                appointment.getSeller().getName()
        );
    }
}
