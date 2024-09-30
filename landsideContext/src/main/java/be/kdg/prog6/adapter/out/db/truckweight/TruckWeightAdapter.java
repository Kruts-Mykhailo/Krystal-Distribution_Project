package be.kdg.prog6.adapter.out.db.truckweight;

import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import be.kdg.prog6.domain.TruckWeightRecord;
import be.kdg.prog6.port.out.TruckWeightSavedPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TruckWeightAdapter implements TruckWeightSavedPort {

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
}
