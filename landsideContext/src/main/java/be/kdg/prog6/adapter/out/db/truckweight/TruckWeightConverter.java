package be.kdg.prog6.adapter.out.db.truckweight;

import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.TruckWeightRecord;

public class TruckWeightConverter {

    public static TruckWeightJpaEntity toJpa(TruckWeightRecord truckWeightRecord) {
        return new TruckWeightJpaEntity(
                truckWeightRecord.licensePlate().licensePlate(),
                truckWeightRecord.weight(),
                truckWeightRecord.time()
        );
    }

    public static TruckWeightRecord fromJpa(TruckWeightJpaEntity truckWeightJpaEntity) {
        return new TruckWeightRecord(
                new LicensePlate(truckWeightJpaEntity.getLicensePlate()),
                truckWeightJpaEntity.getWeightRecorded(),
                truckWeightJpaEntity.getRecordTime()
        );
    }
}
