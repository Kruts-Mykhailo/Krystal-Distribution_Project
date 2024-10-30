package be.kdg.prog6.adapter.out.db.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface AppointmentJpaRepository extends JpaRepository<AppointmentJpaEntity, UUID> {

    @Query(value = "SELECT * FROM appointments a " +
            "WHERE a.license_plate = :licensePlate " +
            "AND :arrivalDateTime >= a.appointment_date_time " +
            "ORDER BY a.appointment_date_time " +
            "LIMIT 1", nativeQuery = true)
    Optional<AppointmentJpaEntity> findEarliestScheduledAppointmentWithArrivalDateTime(
            @Param("licensePlate") String licensePlate,
            @Param("arrivalDateTime") LocalDateTime arrivalDateTime
    );

    @Query("select a from AppointmentJpaEntity a " +
    "left join fetch a.activities " +
    "left join fetch a.seller " +
    "where a.licensePlate = :licensePlate and a.status != :status ")
    Optional<AppointmentJpaEntity> findByLicensePlateAndNotStatusFetched(String licensePlate, String status);

    @Query("select a from AppointmentJpaEntity a " +
    "left join fetch a.seller " +
    "where a.appointmentDateTime >= :from and a.appointmentDateTime <= :to ")
    List<AppointmentJpaEntity> findAllByAppointmentDateTimeBetweenFetched(LocalDateTime from, LocalDateTime to);

    List<AppointmentJpaEntity> findAllByStatusIn(List<String> statuses);

}
