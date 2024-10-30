package be.kdg.prog6.adapter.out.db.purchaseOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PurchaseOrderJpaRepository extends JpaRepository<PurchaseOrderJpaEntity, String> {

    @Query("select p from PurchaseOrderJpaEntity p " +
    "left join fetch p.orderLines " +
    "left join fetch p.seller " +
    "where p.poNumber = :number")
    Optional<PurchaseOrderJpaEntity> findByPurchaseOrderNumberFetched(String number);


    @Query("select p from PurchaseOrderJpaEntity p " +
            "left join fetch p.seller ")
    List<PurchaseOrderJpaEntity> findAllWithSellerFetched();
}
