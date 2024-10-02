package be.kdg.prog6.core;

import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.LeaveWeighingBridgeUseCase;
import be.kdg.prog6.port.in.PassBridgeCommand;
import be.kdg.prog6.port.out.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;


@Service
public class LeaveWeighingBridgeUseCaseImpl implements LeaveWeighingBridgeUseCase {

    private final AppointmentFoundPort appointmentFoundPort;
    private final AppointmentUpdatedPort appointmentUpdatedPort;
    private final TruckWeightRecordFoundPort truckWeightRecordFoundPort;
    private final CreatePdtPort createPdtPort;

    public LeaveWeighingBridgeUseCaseImpl(AppointmentFoundPort appointmentFoundPort, AppointmentUpdatedPort appointmentUpdatedPort, TruckWeightRecordFoundPort truckWeightRecordFoundPort, TruckWeightSavedPort truckWeightSavedPort, CreatePdtPort createPdtPort, WarehouseInfoPort warehouseInfoPort) {
        this.appointmentFoundPort = appointmentFoundPort;
        this.appointmentUpdatedPort = appointmentUpdatedPort;
        this.truckWeightRecordFoundPort = truckWeightRecordFoundPort;
        this.createPdtPort = createPdtPort;
    }


    @Override
    @Transactional
    public Optional<WBT> leaveWeighingBridge(PassBridgeCommand passBridgeCommand) {
        Optional<Appointment> optionalAppointment = appointmentFoundPort.getTruckAppointmentOnSite(passBridgeCommand.licensePlate());
        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.leaveByWeighingBridge(LocalDateTime.now());
            TruckWeightRecord truckWeightRecord = new TruckWeightRecord(
                    passBridgeCommand.licensePlate(),
                    passBridgeCommand.weight(),
                    LocalDateTime.now()
            );
            TruckWeightRecord enterWeightRecord = truckWeightRecordFoundPort.getTruckWeightRecord(appointment.getId());
            Double netWeight = enterWeightRecord.weight() - truckWeightRecord.weight();

            createPdtPort.sendPdt(new PDT(
                    appointment.getWarehouseId(),
                    LocalDateTime.now(),
                    netWeight,
                    appointment.getMaterialType()));

            appointmentUpdatedPort.updateAppointment(appointment, AppointmentStatus.LEFT);
            return Optional.of(new WBT(
                    passBridgeCommand.licensePlate(),
                    enterWeightRecord.weight(),
                    truckWeightRecord.weight(),
                    netWeight,
                    enterWeightRecord.time(),
                    truckWeightRecord.time()));
        }
        return Optional.empty();

    }
}
