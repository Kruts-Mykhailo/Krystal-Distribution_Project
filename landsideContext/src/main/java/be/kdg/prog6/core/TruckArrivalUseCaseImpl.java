package be.kdg.prog6.core;

import be.kdg.prog6.adapter.exceptions.AppointmentNotFoundException;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.TruckArrivalCommand;
import be.kdg.prog6.port.in.TruckArrivalUseCase;
import be.kdg.prog6.port.out.AppointmentFoundPort;
import be.kdg.prog6.port.out.AppointmentUpdatedPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.logging.Logger;

@Service
public class TruckArrivalUseCaseImpl implements TruckArrivalUseCase {

    private final AppointmentUpdatedPort appointmentUpdatedPort;
    private final AppointmentFoundPort appointmentFoundPort;

    private final Logger logger = Logger.getLogger(TruckArrivalUseCaseImpl.class.getName());

    public TruckArrivalUseCaseImpl(AppointmentUpdatedPort appointmentUpdatedPort, AppointmentFoundPort appointmentFoundPort) {
        this.appointmentUpdatedPort = appointmentUpdatedPort;
        this.appointmentFoundPort = appointmentFoundPort;
    }

    @Override
    @Transactional
    public void arriveToFacility(TruckArrivalCommand truckArrivalCommand) {
        LicensePlate licensePlate = truckArrivalCommand.licensePlate();
        LocalDateTime arrivalTime = truckArrivalCommand.arrivalTime();

        Appointment appointment = appointmentFoundPort.getAppointmentByArrivalTime(licensePlate, arrivalTime);


        appointment.truckArrived(arrivalTime);
        appointmentUpdatedPort.update(appointment);

        logger.info(String.format("Truck %s arrived to facility at %s", licensePlate.licensePlate(), arrivalTime));


    }
}
