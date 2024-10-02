package be.kdg.prog6.core;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.port.in.DumpPayloadUseCase;
import be.kdg.prog6.port.out.AppointmentFoundPort;
import be.kdg.prog6.port.out.AppointmentUpdatedPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DumpPayloadUseCaseImpl implements DumpPayloadUseCase {

    private final AppointmentFoundPort appointmentFoundPort;
    private final AppointmentUpdatedPort appointmentUpdatedPort;
    private final Logger logger = Logger.getLogger(DumpPayloadUseCaseImpl.class.getName());

    public DumpPayloadUseCaseImpl(AppointmentFoundPort appointmentFoundPort, AppointmentUpdatedPort appointmentUpdatedPort) {
        this.appointmentFoundPort = appointmentFoundPort;
        this.appointmentUpdatedPort = appointmentUpdatedPort;
    }

    @Override
    @Transactional
    public void dumpPayload(LicensePlate licensePlate) {
        Optional<Appointment> appointment = appointmentFoundPort.getTruckAppointmentOnSite(licensePlate);
        if (appointment.isPresent()) {
            Appointment appointmentFound = appointment.get();
            appointmentFound.dumpPayload(LocalDateTime.now());
            appointmentUpdatedPort.updateAppointment(appointmentFound, appointmentFound.getAppointmentStatus());
            logger.info(String.format("Truck %s dumped material on conveyor belt.", licensePlate.licensePlate()));
        }
    }
}
