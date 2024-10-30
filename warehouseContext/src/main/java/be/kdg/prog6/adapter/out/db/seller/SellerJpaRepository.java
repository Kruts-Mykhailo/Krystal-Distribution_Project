package be.kdg.prog6.adapter.out.db.seller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SellerJpaRepository extends JpaRepository<SellerJpaEntity, UUID> {
}
