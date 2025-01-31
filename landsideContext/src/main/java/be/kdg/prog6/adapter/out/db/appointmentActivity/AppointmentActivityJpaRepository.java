package be.kdg.prog6.adapter.out.db.appointmentActivity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AppointmentActivityJpaRepository extends JpaRepository<AppointmentActivityJpaEntity, UUID> {
}
