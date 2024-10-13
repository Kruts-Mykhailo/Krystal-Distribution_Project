package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.exceptions.WarehouseNotFoundException;
import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.WarehouseInfo;
import be.kdg.prog6.port.out.GetSellerByWarehousePort;
import be.kdg.prog6.port.out.WarehouseInfoPort;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

@Component
public class WarehouseInfoDatabaseAdapter implements WarehouseInfoPort, GetSellerByWarehousePort {

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
                warehouseInfoJpa.getInitialCapacity(),
                warehouseInfoJpa.getMaxCapacity());
    }

    @Override
    public void updateWarehouse(UUID warehouseId, Double value) {
        Optional<WarehouseInfoJpaEntity> warehouse = warehouseInfoJpaRepository.findById(warehouseId);

        if (warehouse.isPresent()) {
            WarehouseInfoJpaEntity warehouseInfo = warehouse.get();
            warehouseInfo.setInitialCapacity(value);
            warehouseInfoJpaRepository.save(warehouseInfo);
        }

    }

    @Override
    public Seller.SellerId getSellerByWarehouseId(UUID warehouseId) {
        WarehouseInfoJpaEntity warehouse = warehouseInfoJpaRepository.findById(warehouseId).
                orElseThrow(() -> new WarehouseNotFoundException("Warehouse with id %s not found".formatted(warehouseId)));
        return WarehouseInfoConverter.convert(warehouse).sellerId();


    }
}
