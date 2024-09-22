package be.kdg.prog6.port.in;


import be.kdg.prog6.domain.Appointment;

public interface MakeAppointmentUseCase {
    Appointment makeAppointment(CreateAppointmentCommand createAppointmentCommand);
}
