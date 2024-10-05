package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.WarehouseInfo;
import be.kdg.prog6.port.out.WarehouseInfoPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class WarehouseInfoDatabaseAdapter implements WarehouseInfoPort {

    private final WarehouseInfoJpaRepository warehouseInfoJpaRepository;

    public WarehouseInfoDatabaseAdapter(WarehouseInfoJpaRepository warehouseInfoJpaRepository) {
        this.warehouseInfoJpaRepository = warehouseInfoJpaRepository;
    }

    @Override
    public WarehouseInfo getWarehouse(Seller.SellerId sellerId, MaterialType materialType) {
        Optional<WarehouseInfoJpaEntity> warehouse = warehouseInfoJpaRepository.
                findBySellerIdAndMaterialType(sellerId.id(), materialType.name());

        if (warehouse.isEmpty()) {
            return null;
        }

        WarehouseInfoJpaEntity warehouseInfoJpa = warehouse.get();

        return new WarehouseInfo(
                MaterialType.valueOf(warehouseInfoJpa.getMaterialType()),
                new Seller.SellerId(warehouseInfoJpa.getSellerId()),
                warehouseInfoJpa.getWarehouseId(),
                warehouseInfoJpa.getWarehouseNumber(),
                warehouseInfoJpa.isFullCapacity());
    }

    public WarehouseInfo getWarehouseById(UUID warehouseId) {
        Optional<WarehouseInfoJpaEntity> warehouse = warehouseInfoJpaRepository.findById(warehouseId);
        if (warehouse.isEmpty()) {
            throw new RuntimeException();
        }
        WarehouseInfoJpaEntity warehouseInfoJpa = warehouse.get();
        return new WarehouseInfo(
                MaterialType.valueOf(warehouseInfoJpa.getMaterialType()),
                new Seller.SellerId(warehouseInfoJpa.getSellerId()),
                warehouseInfoJpa.getWarehouseId(),
                warehouseInfoJpa.getWarehouseNumber(),
                warehouseInfoJpa.isFullCapacity());
    }

    @Override
    public void updateWarehouse(UUID warehouseId, boolean isFull) {
        Optional<WarehouseInfoJpaEntity> warehouse = warehouseInfoJpaRepository.findById(warehouseId);

        if (warehouse.isPresent()) {
            WarehouseInfoJpaEntity warehouseInfo = warehouse.get();
            warehouseInfo.setFullCapacity(isFull);
            warehouseInfoJpaRepository.save(warehouseInfo);
        }

    }
}
