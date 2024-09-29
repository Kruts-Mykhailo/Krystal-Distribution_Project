package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.Appointment;

import java.util.UUID;

public interface AppointmentCreatedPort {
    void saveAppointment(Appointment appointment, UUID scheduleId);
}
