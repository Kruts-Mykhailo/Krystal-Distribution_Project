package be.kdg.prog6.adapter.out.db.truckweight;

import be.kdg.prog6.adapter.exceptions.TruckWeightRecordNotFoundException;
import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaRepository;
import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.TruckWeightRecord;
import be.kdg.prog6.port.out.TruckWeightRecordFoundPort;
import be.kdg.prog6.port.out.TruckWeightSavedPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TruckWeightAdapter implements TruckWeightSavedPort, TruckWeightRecordFoundPort {

    private final TruckWeightJpaRepository truckWeightJpaRepository;
    private final AppointmentJpaRepository appointmentJpaRepository;

    public TruckWeightAdapter(TruckWeightJpaRepository truckWeightJpaRepository, AppointmentJpaRepository appointmentJpaRepository) {
        this.truckWeightJpaRepository = truckWeightJpaRepository;
        this.appointmentJpaRepository = appointmentJpaRepository;
    }

    @Override
    public void saveTruckWeight(TruckWeightRecord truckWeightRecord, UUID appointmentId) {
        AppointmentJpaEntity appointmentJpaEntity = appointmentJpaRepository.getReferenceById(appointmentId);
        TruckWeightJpaEntity truckWeightJpaEntity = TruckWeightConverter.toJpa(truckWeightRecord);

        truckWeightJpaEntity.setAppointment(appointmentJpaEntity);
        truckWeightJpaRepository.save(truckWeightJpaEntity);
    }

    @Override
    public TruckWeightRecord getTruckWeightRecord(UUID appointmentId) {
        return TruckWeightConverter.fromJpa(truckWeightJpaRepository.findByAppointmentAppointmentId(appointmentId)
                .orElseThrow(() -> new TruckWeightRecordNotFoundException("Truck weight record not found")));

    }

}
