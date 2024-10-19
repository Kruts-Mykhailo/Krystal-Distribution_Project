package be.kdg.prog6.core;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.PDT;
import be.kdg.prog6.events.PayloadDeliveredEvent;
import be.kdg.prog6.port.in.DumpPayloadUseCase;
import be.kdg.prog6.port.out.AppointmentFoundPort;
import be.kdg.prog6.port.out.AppointmentUpdatedPort;
import be.kdg.prog6.port.out.CreatePdtPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class DumpPayloadUseCaseImpl implements DumpPayloadUseCase {

    private final AppointmentFoundPort appointmentFoundPort;
    private final AppointmentUpdatedPort appointmentUpdatedPort;
    private final CreatePdtPort createPdtPort;
    private final Logger logger = Logger.getLogger(DumpPayloadUseCaseImpl.class.getName());

    public DumpPayloadUseCaseImpl(AppointmentFoundPort appointmentFoundPort, AppointmentUpdatedPort appointmentUpdatedPort, CreatePdtPort createPdtPort) {
        this.appointmentFoundPort = appointmentFoundPort;
        this.appointmentUpdatedPort = appointmentUpdatedPort;
        this.createPdtPort = createPdtPort;
    }

    @Override
    @Transactional
    public PDT dumpPayload(LicensePlate licensePlate) {
        Appointment appointment = appointmentFoundPort.getTruckAppointmentOnSite(licensePlate)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Truck %s is not recognized on conveyor belt".formatted(licensePlate.licensePlate())
                ));

        appointment.dumpPayload(LocalDateTime.now());
        appointmentUpdatedPort.updateAppointment(appointment, appointment.getAppointmentStatus());
        createPdtPort.sendPdt(new PayloadDeliveredEvent(
                appointment.getWarehouseId(),
                LocalDateTime.now(),
                0.0,
                appointment.getMaterialType().name()));
        logger.info(String.format("Truck %s dumped material on conveyor belt.", licensePlate.licensePlate()));

        return new PDT(appointment.getWarehouseNumber(), LocalDateTime.now(), appointment.getMaterialType());
    }
}
