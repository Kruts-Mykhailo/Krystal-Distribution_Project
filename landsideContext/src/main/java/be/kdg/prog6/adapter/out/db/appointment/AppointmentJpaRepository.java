package be.kdg.prog6.adapter.out.db.appointment;

import be.kdg.prog6.domain.AppointmentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
    Optional<AppointmentJpaEntity> findEarliestScheduledAppointmentWithArrivalDateTime(
            @Param("licensePlate") String licensePlate,
            @Param("arrivalDateTime") LocalDateTime arrivalDateTime
    );

    Optional<AppointmentJpaEntity> findByLicensePlateAndStatus(String licensePlate, String status);
}
