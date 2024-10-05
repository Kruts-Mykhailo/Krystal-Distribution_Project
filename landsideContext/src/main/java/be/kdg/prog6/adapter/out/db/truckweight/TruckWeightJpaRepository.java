package be.kdg.prog6.adapter.out.db.truckweight;

import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import be.kdg.prog6.domain.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface TruckWeightJpaRepository extends JpaRepository<TruckWeightJpaEntity, UUID> {
    TruckWeightJpaEntity findByAppointment(AppointmentJpaEntity appointment);
}
