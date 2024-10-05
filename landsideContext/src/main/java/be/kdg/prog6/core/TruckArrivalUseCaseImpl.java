package be.kdg.prog6.core;

import be.kdg.prog6.adapter.exceptions.AppointmentNotFoundException;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.TruckArrivalUseCase;
import be.kdg.prog6.port.out.AppointmentUpdatedPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
    public void arriveToFacility(LicensePlate licensePlate, LocalDateTime arrivalTime) {
        Appointment appointment = appointmentUpdatedPort.getAppointmentByArrivalTime(licensePlate, arrivalTime)
                .orElseThrow(() -> new AppointmentNotFoundException(
                        String.format("Appointment for %s has not been found", licensePlate.licensePlate())
                ));

        appointment.truckArrived(arrivalTime);
        logger.info(String.format("Truck %s arrived to facility at %s", licensePlate.licensePlate(), arrivalTime));
        appointmentUpdatedPort.updateAppointment(appointment, AppointmentStatus.ON_SITE);

    }
}
