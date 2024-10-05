package be.kdg.prog6.adapter.out.db.payloadActivity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PayloadActivityJpaRepository extends JpaRepository<PayloadActivityJpaEntity, UUID> {
}
