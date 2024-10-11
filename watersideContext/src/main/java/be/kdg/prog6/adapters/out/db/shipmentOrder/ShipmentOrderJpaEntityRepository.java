package be.kdg.prog6.adapters.out.db.shipmentOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShipmentOrderJpaEntityRepository extends JpaRepository<ShipmentOrderJpaEntity, String> {


    @Query("select s from ShipmentOrderJpaEntity  s " +
    "left join fetch s.shipmentOrderLines " +
    "where s.poReferenceNumber = :orderId ")
    Optional<ShipmentOrderJpaEntity> findOrderByIdFetched(String orderId);

    @Query("select s from ShipmentOrderJpaEntity  s " +
    "left join fetch s.shipmentOrderLines " +
    "where s.vesselNumber = :vesselNumber " +
    "and s.isMatchedWithPO = false ")
    Optional<ShipmentOrderJpaEntity> findOrderByVesselNumberFetched(String vesselNumber);
}
