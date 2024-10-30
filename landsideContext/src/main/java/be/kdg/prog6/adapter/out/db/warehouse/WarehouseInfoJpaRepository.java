package be.kdg.prog6.adapter.out.db.warehouse;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseInfoJpaRepository extends JpaRepository<WarehouseInfoJpaEntity, UUID> {


    @Query("select w from WarehouseInfoJpaEntity w " +
            "left join fetch w.seller " +
            "where w.sellerId = :sellerId and w.materialType = :materialType")
    Optional<WarehouseInfoJpaEntity> findBySellerIdAndMaterialTypeFetched(UUID sellerId, String materialType);

    @Query("select w from WarehouseInfoJpaEntity w " +
    "left join fetch w.seller " +
    "where w.warehouseNumber = :warehouseNumber ")
    Optional<WarehouseInfoJpaEntity> findByWarehouseNumberFetched(String warehouseNumber);
}
