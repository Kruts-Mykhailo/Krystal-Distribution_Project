package be.kdg.prog6.adapter.out.db.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentJpaRepository extends JpaRepository<AppointmentJpaEntity, UUID> {

    @Query("SELECT a FROM AppointmentJpaEntity a " +
            "WHERE a.licensePlate = :licensePlate " +
            "AND a.status = 'SCHEDULED' " +
            "AND :arrivalDateTime >= a.appointmentDateTime " +
            "ORDER BY a.appointmentDateTime ASC")
    Optional<AppointmentJpaEntity> findEarliestScheduledAppointmentWithArrivalDateTime(String licensePlate, LocalDateTime arrivalDateTime);
}
