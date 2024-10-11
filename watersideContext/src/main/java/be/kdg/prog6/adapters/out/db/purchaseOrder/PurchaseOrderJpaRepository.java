package be.kdg.prog6.adapters.out.db.purchaseOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PurchaseOrderJpaRepository extends JpaRepository<PurchaseOrderJpaEntity, String> {

    @Query("select p from PurchaseOrderJpaEntity p " +
    "left join fetch p.orderLines " +
    "where p.poNumber = :number")
    Optional<PurchaseOrderJpaEntity> findByPurchaseOrderNumberFetched(String number);
}
