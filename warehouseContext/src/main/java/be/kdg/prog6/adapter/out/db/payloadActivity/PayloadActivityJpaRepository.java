package be.kdg.prog6.adapter.out.db.payloadActivity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface PayloadActivityJpaRepository extends JpaRepository<PayloadActivityJpaEntity, UUID> {

    @Query("select p from PayloadActivityJpaEntity p " +
    "where p.warehouse.warehouseNumber = :warehouseNumber " +
    "and p.amount = :amount " +
    "and p.recordTime = :recordTime")
    List<PayloadActivityJpaEntity> findFirstByWarehouseAndAmountAndRecordTime(String warehouseNumber, Double amount, LocalDateTime recordTime);
}
