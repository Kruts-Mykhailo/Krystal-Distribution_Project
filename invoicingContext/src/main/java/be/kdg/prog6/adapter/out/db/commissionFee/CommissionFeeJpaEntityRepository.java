package be.kdg.prog6.adapter.out.db.commissionFee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CommissionFeeJpaEntityRepository extends JpaRepository<CommissionJpaEntity, UUID> {
}
