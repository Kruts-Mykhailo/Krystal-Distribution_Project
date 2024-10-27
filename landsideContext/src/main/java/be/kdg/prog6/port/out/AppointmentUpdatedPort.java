package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.TruckArrivalStatus;
import be.kdg.prog6.domain.LicensePlate;

import java.time.LocalDateTime;
import java.util.Optional;

public interface AppointmentUpdatedPort {
    void update(Appointment appointment);

}
