package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.Appointment;

import java.util.List;

public interface AppointmentUpdatedPort {
    void updateStatus(Appointment appointment);
    void updateAllStatuses(List<Appointment> appointments);

}
