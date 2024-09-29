package be.kdg.prog6.adapter.out.db.truckActivity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TruckActivityJpaRepository extends JpaRepository<TruckActivityJpaEntity, UUID> {
}
