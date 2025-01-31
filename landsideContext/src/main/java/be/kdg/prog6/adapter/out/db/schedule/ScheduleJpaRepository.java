package be.kdg.prog6.adapter.out.db.schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ScheduleJpaRepository extends JpaRepository<ScheduleJpaEntity, UUID> {

    @Query("select s from ScheduleJpaEntity s " +
    "left join fetch s.appointmentJpaEntities " +
    "where s.scheduleDate = :scheduleDate")
    Optional<ScheduleJpaEntity> findByScheduleDate(LocalDate scheduleDate);

    @Query("select s from ScheduleJpaEntity s " +
            "left join fetch s.appointmentJpaEntities " +
            "where s.scheduleDate >= :date")
    List<ScheduleJpaEntity> findAllAfterDateFetched(LocalDate date);
}
