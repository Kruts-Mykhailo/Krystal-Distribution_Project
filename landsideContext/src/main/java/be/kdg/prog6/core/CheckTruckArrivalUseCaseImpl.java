package be.kdg.prog6.core;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.port.in.CheckTruckArrivalUseCase;
import be.kdg.prog6.port.out.AppointmentFoundPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CheckTruckArrivalUseCaseImpl implements CheckTruckArrivalUseCase {

    private final AppointmentFoundPort appointmentFoundPort;

    public CheckTruckArrivalUseCaseImpl(AppointmentFoundPort appointmentFoundPort) {
        this.appointmentFoundPort = appointmentFoundPort;
    }

    @Override
    @Transactional
    public Appointment checkTruckArrival(LicensePlate licensePlate) {
        return appointmentFoundPort.getAppointmentOfTruck(licensePlate);
    }
}
