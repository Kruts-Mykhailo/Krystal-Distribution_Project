package be.kdg.prog6.core;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.port.in.CheckAllTrucksStatusesUseCase;
import be.kdg.prog6.port.out.AppointmentFoundPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class CheckTruckStatusesUseCaseImpl implements CheckAllTrucksStatusesUseCase {

    private final AppointmentFoundPort appointmentFoundPort;

    public CheckTruckStatusesUseCaseImpl(AppointmentFoundPort appointmentFoundPort) {
        this.appointmentFoundPort = appointmentFoundPort;
    }

    @Override
    @Transactional
    public List<Appointment> checkStatusesOfTrucks(LocalDate date) {
        return appointmentFoundPort.getAllTruckAppointmentsByDate(date);
    }
}
