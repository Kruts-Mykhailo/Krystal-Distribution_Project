package be.kdg.prog6.adapter.out.db.payloadActivity;

import be.kdg.prog6.adapter.out.db.warehouse.WarehouseJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PayloadActivityJpaRepository extends JpaRepository<PayloadActivityJpaEntity, UUID> {

    Optional<PayloadActivityJpaEntity> findFirstByWarehouseAndAmountOrderByRecordTimeAsc(WarehouseJpaEntity warehouse, Double amount);
    Optional<PayloadActivityJpaEntity> findFirstByWarehouseAndAmountAndRecordTimeOrderByRecordTimeAsc(WarehouseJpaEntity warehouse, Double amount, LocalDateTime recordTime);
}
