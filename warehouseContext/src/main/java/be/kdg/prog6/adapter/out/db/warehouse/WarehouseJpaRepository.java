package be.kdg.prog6.adapter.out.db.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseJpaRepository extends JpaRepository<WarehouseJpaEntity, String> {

    Optional<WarehouseJpaEntity> findBySellerIdAndMaterialType(UUID ownerId, String materialType);

    @Query("select w from WarehouseJpaEntity w " +
            "left join fetch w.payloadActivityJpaEntities " +
            "left join fetch w.seller " +
            "where w.warehouseNumber = :warehouseNumber")
    Optional<WarehouseJpaEntity> findByWarehouseNumberFetched(String warehouseNumber);

    @Query("select w from WarehouseJpaEntity w " +
            "left join fetch w.seller ")
    List<WarehouseJpaEntity> findAllFetchedSeller();

}
