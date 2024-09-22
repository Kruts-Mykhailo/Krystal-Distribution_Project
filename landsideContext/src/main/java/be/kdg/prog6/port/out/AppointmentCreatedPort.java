package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.Appointment;

public interface AppointmentCreatedPort {
    Appointment saveAppointment(Appointment appointment);
}
