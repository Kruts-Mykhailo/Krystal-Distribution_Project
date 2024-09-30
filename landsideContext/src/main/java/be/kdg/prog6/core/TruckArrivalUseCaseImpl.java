package be.kdg.prog6.core;

import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.TruckArrivalUseCase;
import be.kdg.prog6.port.out.AppointmentUpdatedPort;
import be.kdg.prog6.port.out.TruckActivitySavedPort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TruckArrivalUseCaseImpl implements TruckArrivalUseCase {

    private final AppointmentUpdatedPort appointmentUpdatedPort;

    private final TruckActivitySavedPort truckActivitySavedPort;

    private final Logger logger = Logger.getLogger(TruckArrivalUseCaseImpl.class.getName());

    public TruckArrivalUseCaseImpl(AppointmentUpdatedPort appointmentUpdatedPort, TruckActivitySavedPort truckActivitySavedPort) {
        this.appointmentUpdatedPort = appointmentUpdatedPort;
        this.truckActivitySavedPort = truckActivitySavedPort;
    }

    @Override
    public Optional<TruckActivity> arriveToFacility(LicensePlate licensePlate, LocalDateTime arrivalTime) {
        Optional<Appointment> appointment = appointmentUpdatedPort.getAppointmentByArrivalTime(licensePlate, arrivalTime);

        if (appointment.isPresent()) {
            logger.info("Appointment found");

            Appointment foundAppointment = appointment.get();

            AppointmentStatus appointmentStatus = arrivalTime.isAfter(foundAppointment.getAppointmentDateTime()) &&
                    arrivalTime.isBefore(foundAppointment.getAppointmentDateTime().plusHours(1))
                    ? AppointmentStatus.ON_TIME : AppointmentStatus.LATE;

            TruckActivity truckActivity = new TruckActivity(
                    licensePlate,
                    ActivityType.ARRIVAL,
                    arrivalTime,
                    appointmentStatus
            );
            truckActivitySavedPort.saveTruckActivity(truckActivity);
            appointmentUpdatedPort.updateAppointmentStatus(AppointmentStatus.ON_SITE, foundAppointment.getId());
            return Optional.of(truckActivity);
        }
        logger.warning("Appointment not found");
        return Optional.empty();
    }
}
