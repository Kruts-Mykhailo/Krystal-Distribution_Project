package be.kdg.prog6.adapter.out.db.truckweight;

import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.TruckWeightRecord;
import be.kdg.prog6.port.out.TruckWeightRecordFoundPort;
import be.kdg.prog6.port.out.TruckWeightSavedPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TruckWeightAdapter implements TruckWeightSavedPort, TruckWeightRecordFoundPort {

    private final TruckWeightJpaRepository truckWeightJpaRepository;

    public TruckWeightAdapter(TruckWeightJpaRepository truckWeightJpaRepository) {
        this.truckWeightJpaRepository = truckWeightJpaRepository;
    }

    @Override
    public void saveTruckWeight(TruckWeightRecord truckWeightRecord, UUID appointmentId) {
        truckWeightJpaRepository.save( new TruckWeightJpaEntity(
                truckWeightRecord.licensePlate().licensePlate(),
                truckWeightRecord.weight(),
                truckWeightRecord.time(),
                new AppointmentJpaEntity(appointmentId)
        ));
    }

    @Override
    public TruckWeightRecord getTruckWeightRecord(UUID appointmentId) {
        return toTruckWeightRecord(
                truckWeightJpaRepository.findByAppointment(new AppointmentJpaEntity(appointmentId))
        );
    }

    private TruckWeightRecord toTruckWeightRecord(TruckWeightJpaEntity truckWeightJpaEntity) {
        return new TruckWeightRecord(
                new LicensePlate(truckWeightJpaEntity.getLicensePlate()),
                truckWeightJpaEntity.getWeightRecorded(),
                truckWeightJpaEntity.getRecordTime()
        );
    }
}
