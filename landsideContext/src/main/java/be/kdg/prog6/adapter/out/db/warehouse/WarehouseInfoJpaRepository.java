package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.domain.WarehouseInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WarehouseInfoJpaRepository extends JpaRepository<WarehouseInfoJpaEntity, UUID> {
    Optional<WarehouseInfoJpaEntity> findBySellerIdAndMaterialType(UUID sellerId, String materialType);
    Optional<WarehouseInfoJpaEntity> findByWarehouseNumber(String warehouseNumber);
}
