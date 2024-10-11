package be.kdg.prog6.adapters.out.db.shipmentOrderLine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ShipmentOrderLineJpaEntityRepository extends JpaRepository<ShipmentOrderLineJpaEntity, UUID> {
}
