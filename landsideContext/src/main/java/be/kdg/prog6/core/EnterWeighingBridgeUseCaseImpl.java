package be.kdg.prog6.core;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.AppointmentStatus;
import be.kdg.prog6.domain.TruckWeightRecord;
import be.kdg.prog6.port.in.PassBridgeCommand;
import be.kdg.prog6.port.in.EnterWeighingBridgeUseCase;
import be.kdg.prog6.port.out.AppointmentFoundPort;
import be.kdg.prog6.port.out.AppointmentUpdatedPort;
import be.kdg.prog6.port.out.TruckWeightSavedPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class EnterWeighingBridgeUseCaseImpl implements EnterWeighingBridgeUseCase {

    private final AppointmentFoundPort appointmentFoundPort;
    private final AppointmentUpdatedPort appointmentUpdatedPort;
    private final TruckWeightSavedPort truckWeightSavedPort;

    public EnterWeighingBridgeUseCaseImpl(AppointmentFoundPort appointmentFoundPort, AppointmentUpdatedPort appointmentUpdatedPort, TruckWeightSavedPort truckWeightSavedPort) {
        this.appointmentFoundPort = appointmentFoundPort;
        this.appointmentUpdatedPort = appointmentUpdatedPort;
        this.truckWeightSavedPort = truckWeightSavedPort;
    }

    @Override
    @Transactional
    public void enterWeighingBridge(PassBridgeCommand passBridgeCommand) {
        Optional<Appointment> optionalAppointment = appointmentFoundPort.getTruckAppointmentOnSite(passBridgeCommand.licensePlate());

        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();

            appointment.enterByWeighingBridge(passBridgeCommand.time());

            TruckWeightRecord truckWeightRecord = new TruckWeightRecord(
                    passBridgeCommand.licensePlate(),
                    passBridgeCommand.weight(),
                    LocalDateTime.now()
            );
            truckWeightSavedPort.saveTruckWeight(truckWeightRecord, appointment.getId());
            appointmentUpdatedPort.updateAppointment(appointment, AppointmentStatus.ON_SITE);
        }
    }
}
