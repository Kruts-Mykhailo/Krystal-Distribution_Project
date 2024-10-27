package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.TruckArrivalStatus;
import be.kdg.prog6.domain.LicensePlate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentFoundPort {
    Appointment getByLicensePlateAndNotStatus(LicensePlate licensePlate, TruckArrivalStatus status);
    List<Appointment> getAllTruckAppointmentsByDate(LocalDate when);
    List<Appointment> getAllAppointmentsByStatusIn(List<TruckArrivalStatus> status);
    Appointment getAppointmentByArrivalTime(LicensePlate licensePlate, LocalDateTime arrivalTime);
}
