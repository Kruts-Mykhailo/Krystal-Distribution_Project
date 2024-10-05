package be.kdg.prog6.core;

import be.kdg.prog6.adapter.in.exceptions.AppointmentNotFoundException;
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
import java.util.logging.Logger;

@Service
public class EnterWeighingBridgeUseCaseImpl implements EnterWeighingBridgeUseCase {

    private final AppointmentFoundPort appointmentFoundPort;
    private final AppointmentUpdatedPort appointmentUpdatedPort;
    private final TruckWeightSavedPort truckWeightSavedPort;
    private final Logger logger =  Logger.getLogger(EnterWeighingBridgeUseCaseImpl.class.getName());

    public EnterWeighingBridgeUseCaseImpl(AppointmentFoundPort appointmentFoundPort, AppointmentUpdatedPort appointmentUpdatedPort, TruckWeightSavedPort truckWeightSavedPort) {
        this.appointmentFoundPort = appointmentFoundPort;
        this.appointmentUpdatedPort = appointmentUpdatedPort;
        this.truckWeightSavedPort = truckWeightSavedPort;
    }

    @Override
    @Transactional
    public void enterWeighingBridge(PassBridgeCommand passBridgeCommand) {
        Appointment appointment = appointmentFoundPort.getTruckAppointmentOnSite(passBridgeCommand.licensePlate())
                .orElseThrow(() -> new AppointmentNotFoundException("Truck on site not recognized"));

        appointment.enterByWeighingBridge(passBridgeCommand.time());
        TruckWeightRecord truckWeightRecord = new TruckWeightRecord(
                passBridgeCommand.licensePlate(),
                passBridgeCommand.weight(),
                LocalDateTime.now()
        );
        logger.info(String.format(
                "Truck %s passed weighing bridge at %s",
                passBridgeCommand.licensePlate().licensePlate(),
                truckWeightRecord
        ));
        truckWeightSavedPort.saveTruckWeight(truckWeightRecord, appointment.getId());
        appointmentUpdatedPort.updateAppointment(appointment, AppointmentStatus.ON_SITE);

    }
}
