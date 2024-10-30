package be.kdg.prog6.adapters.out.db.shipmentOrder;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ShipmentOrderJpaEntityRepository extends JpaRepository<ShipmentOrderJpaEntity, String> {

    @Query("select s from ShipmentOrderJpaEntity  s " +
    "where s.vesselNumber = :vesselNumber " +
    "and s.shipmentStatus != :shipmentStatus ")
    Optional<ShipmentOrderJpaEntity> findByVesselNumberAndNotStatusFetched(String vesselNumber, String shipmentStatus);

    List<ShipmentOrderJpaEntity> findAllByBunkeringOperationDate(LocalDate bunkeringOperationDate);

    @Query("select s from ShipmentOrderJpaEntity s " +
    "where s.inspectionOperationDate = null " +
    "and s.inspectorSignature = null")
    List<ShipmentOrderJpaEntity> findAllByEmptyIOCustom();

    List<ShipmentOrderJpaEntity> findAllByShipmentStatusIsNot(String shipmentStatus);
}
