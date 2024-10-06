package be.kdg.prog6.core;

import be.kdg.prog6.adapter.exceptions.AppointmentNotFoundException;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.LeaveWeighingBridgeUseCase;
import be.kdg.prog6.port.in.PassBridgeCommand;
import be.kdg.prog6.port.out.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.logging.Logger;


@Service
public class LeaveWeighingBridgeUseCaseImpl implements LeaveWeighingBridgeUseCase {

    private final AppointmentFoundPort appointmentFoundPort;
    private final AppointmentUpdatedPort appointmentUpdatedPort;
    private final TruckWeightRecordFoundPort truckWeightRecordFoundPort;
    private final TruckWeightSavedPort truckWeightSavedPort;
    private final CreatePdtPort createPdtPort;
    private final Logger logger = Logger.getLogger(LeaveWeighingBridgeUseCaseImpl.class.getName());

    public LeaveWeighingBridgeUseCaseImpl(AppointmentFoundPort appointmentFoundPort, AppointmentUpdatedPort appointmentUpdatedPort, TruckWeightRecordFoundPort truckWeightRecordFoundPort, TruckWeightSavedPort truckWeightSavedPort, CreatePdtPort createPdtPort, WarehouseInfoPort warehouseInfoPort, TruckWeightSavedPort truckWeightSavedPort1) {
        this.appointmentFoundPort = appointmentFoundPort;
        this.appointmentUpdatedPort = appointmentUpdatedPort;
        this.truckWeightRecordFoundPort = truckWeightRecordFoundPort;
        this.createPdtPort = createPdtPort;
        this.truckWeightSavedPort = truckWeightSavedPort1;
    }


    @Override
    @Transactional
    public WBT leaveWeighingBridge(PassBridgeCommand passBridgeCommand) {
        Appointment appointment = appointmentFoundPort.getTruckAppointmentOnSite(passBridgeCommand.licensePlate())
                .orElseThrow(() -> new AppointmentNotFoundException("Truck on site not recognized"));

        appointment.leaveByWeighingBridge(LocalDateTime.now());
        TruckWeightRecord truckWeightRecord = new TruckWeightRecord(
                passBridgeCommand.licensePlate(),
                passBridgeCommand.weight(),
                LocalDateTime.now()
        );
        TruckWeightRecord enterWeightRecord = truckWeightRecordFoundPort.getTruckWeightRecord(appointment.getId());
        Double netWeight = enterWeightRecord.weight() - truckWeightRecord.weight();


        truckWeightSavedPort.saveTruckWeight(truckWeightRecord, appointment.getId());
        appointmentUpdatedPort.updateAppointment(appointment, AppointmentStatus.LEFT_SITE);
        createPdtPort.sendPdt(new PDT(
                appointment.getWarehouseId(),
                LocalDateTime.now(),
                netWeight,
                appointment.getMaterialType()));
        logger.info(String.format("Truck %s left site", passBridgeCommand.licensePlate().licensePlate()));
        return new WBT(
                passBridgeCommand.licensePlate(),
                enterWeightRecord.weight(),
                truckWeightRecord.weight(),
                netWeight,
                enterWeightRecord.time(),
                truckWeightRecord.time());

    }
}
