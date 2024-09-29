package be.kdg.prog6.port.in;


import be.kdg.prog6.domain.Appointment;

import java.util.Optional;

public interface MakeAppointmentUseCase {
    Optional<Appointment> makeAppointment(CreateAppointmentCommand createAppointmentCommand);
}
