package be.kdg.prog6.adapter.out.db.orderLine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderLineJpaRepository extends JpaRepository<OrderLineJpaEntity, UUID> {
}
