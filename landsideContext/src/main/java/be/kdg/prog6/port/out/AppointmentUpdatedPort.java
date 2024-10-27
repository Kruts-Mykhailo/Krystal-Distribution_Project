package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.TruckArrivalStatus;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentUpdatedPort {
    void update(Appointment appointment);
    void updateAllByStatus(List<Appointment> appointments, TruckArrivalStatus status);

}
