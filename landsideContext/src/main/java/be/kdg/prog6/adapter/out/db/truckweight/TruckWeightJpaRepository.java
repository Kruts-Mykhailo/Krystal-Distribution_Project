package be.kdg.prog6.adapter.out.db.truckweight;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TruckWeightJpaRepository extends JpaRepository<TruckWeightJpaEntity, UUID> {
}
