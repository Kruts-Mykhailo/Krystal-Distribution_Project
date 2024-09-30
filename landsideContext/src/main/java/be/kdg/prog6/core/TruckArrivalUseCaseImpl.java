package be.kdg.prog6.core;

import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.TruckArrivalUseCase;
import be.kdg.prog6.port.out.AppointmentUpdatedPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TruckArrivalUseCaseImpl implements TruckArrivalUseCase {

    private final AppointmentUpdatedPort appointmentUpdatedPort;

    private final Logger logger = Logger.getLogger(TruckArrivalUseCaseImpl.class.getName());

    public TruckArrivalUseCaseImpl(AppointmentUpdatedPort appointmentUpdatedPort) {
        this.appointmentUpdatedPort = appointmentUpdatedPort;
    }

    @Override
    @Transactional
    public Optional<AppointmentActivity> arriveToFacility(LicensePlate licensePlate, LocalDateTime arrivalTime) {
        Optional<Appointment> appointment = appointmentUpdatedPort.getAppointmentByArrivalTime(licensePlate, arrivalTime);

        if (appointment.isPresent()) {
            logger.info("Appointment found");

            Appointment foundAppointment = appointment.get();
            AppointmentActivity appointmentActivity = foundAppointment.truckArrived(arrivalTime);
            appointmentUpdatedPort.updateAppointment(foundAppointment, AppointmentStatus.ON_SITE);

            return Optional.of(appointmentActivity);
        }
        logger.warning("Appointment not found");
        return Optional.empty();
    }
}
