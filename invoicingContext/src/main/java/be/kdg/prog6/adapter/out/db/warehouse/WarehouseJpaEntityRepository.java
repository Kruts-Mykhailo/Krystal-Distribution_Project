package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.domain.MaterialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseJpaEntityRepository extends JpaRepository<WarehouseJpaEntity, UUID> {

    @Query("select w from WarehouseJpaEntity w " +
    "left join fetch w.payloads " +
    "where w.sellerId = :sellerId and w.materialType = :materialType")
    Optional<WarehouseJpaEntity> findBySellerIdAndMaterialTypeFetched(UUID sellerId, String materialType);

    @Query("select w from WarehouseJpaEntity w " +
            "left join fetch w.payloads " +
            "where w.sellerId = :sellerId")
    List<WarehouseJpaEntity> findAllBySellerIdFetched(UUID sellerId);
}
