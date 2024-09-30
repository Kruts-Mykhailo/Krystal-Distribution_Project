package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.LicensePlate;

import java.util.Optional;

public interface AppointmentFoundPort {
    Optional<Appointment> getTruckAppointmentOnSite(LicensePlate licensePlate);
}
