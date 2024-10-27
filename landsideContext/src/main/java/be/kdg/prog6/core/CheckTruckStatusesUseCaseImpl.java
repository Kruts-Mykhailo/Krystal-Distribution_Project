package be.kdg.prog6.core;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.TruckArrivalStatus;
import be.kdg.prog6.port.in.CheckAllTrucksStatusesUseCase;
import be.kdg.prog6.port.out.AppointmentFoundPort;
import be.kdg.prog6.port.out.AppointmentUpdatedPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CheckTruckStatusesUseCaseImpl implements CheckAllTrucksStatusesUseCase {

    private final AppointmentFoundPort appointmentFoundPort;
    private final AppointmentUpdatedPort appointmentUpdatedPort;

    public CheckTruckStatusesUseCaseImpl(AppointmentFoundPort appointmentFoundPort, AppointmentUpdatedPort appointmentUpdatedPort) {
        this.appointmentFoundPort = appointmentFoundPort;
        this.appointmentUpdatedPort = appointmentUpdatedPort;
    }

    @Override
    @Transactional
    public List<Appointment> checkStatusesOfTrucks(LocalDate date) {
        List<Appointment> appointments = appointmentFoundPort.getAllTruckAppointmentsByDate(date);
        appointmentUpdatedPort.updateAllByStatus(appointments.
                stream()
                .filter(a -> a.getWindowEndTime().isBefore(LocalDateTime.now()) &&
                        a.getTruckArrivalStatus() == TruckArrivalStatus.SCHEDULED)
                .toList(),
                TruckArrivalStatus.NOT_ARRIVED_LATE);

        return appointmentFoundPort.getAllTruckAppointmentsByDate(date);
    }
}
