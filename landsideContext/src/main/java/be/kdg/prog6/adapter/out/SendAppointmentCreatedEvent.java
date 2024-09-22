package be.kdg.prog6.adapter.out;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.port.out.AppointmentCreatedPort;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class SendAppointmentCreatedEvent implements AppointmentCreatedPort {
    Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public Appointment saveAppointment(Appointment appointment) {

        logger.info("Appointment: " + appointment + " was successfully saved");
        return appointment;
    }
}
