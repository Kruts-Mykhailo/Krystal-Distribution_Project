package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.AppointmentStatus;
import be.kdg.prog6.domain.LicensePlate;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface AppointmentUpdatedPort {

    void updateAppointmentStatus(AppointmentStatus status, UUID appointmentId);
    Optional<Appointment> getAppointmentByArrivalTime(LicensePlate licensePlate, LocalDateTime arrivalTime);
}
