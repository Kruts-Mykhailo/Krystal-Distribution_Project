package be.kdg.prog6.adapter.in.api.dto;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.AppointmentActivity;
import be.kdg.prog6.domain.AppointmentStatus;

import java.util.Comparator;

public record TruckArrivalDTO(String currentStatus, String arrivalStatus){
    public static TruckArrivalDTO from(Appointment appointment) {
        if (appointment.getAppointmentActivities().isEmpty()) {
           return new TruckArrivalDTO(appointment.getAppointmentStatus().name(), AppointmentStatus.NOT_AVAILABLE.name());
        }

        return new TruckArrivalDTO(
                appointment.getAppointmentStatus().name(),
                appointment.getAppointmentActivities()
                        .stream().min(Comparator.comparing(AppointmentActivity::localDateTime))
                        .map(activity -> activity.status().name())
                        .orElse(AppointmentStatus.NOT_AVAILABLE.name())
        );
    }
}
