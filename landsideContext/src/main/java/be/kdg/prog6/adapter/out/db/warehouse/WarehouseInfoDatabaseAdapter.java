package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.exceptions.WarehouseNotFoundException;
import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.WarehouseInfo;
import be.kdg.prog6.domain.WarehouseNumber;
import be.kdg.prog6.port.out.WarehouseProjectionFoundPort;
import be.kdg.prog6.port.out.WarehouseProjectionUpdatedPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class WarehouseInfoDatabaseAdapter implements WarehouseProjectionFoundPort, WarehouseProjectionUpdatedPort {

    private final WarehouseInfoJpaRepository warehouseInfoJpaRepository;

    public WarehouseInfoDatabaseAdapter(WarehouseInfoJpaRepository warehouseInfoJpaRepository) {
        this.warehouseInfoJpaRepository = warehouseInfoJpaRepository;
    }

    @Override
    public WarehouseInfo getWarehouse(Seller.SellerId sellerId, MaterialType materialType) {
        return WarehouseInfoConverter.convert(warehouseInfoJpaRepository
                .findBySellerIdAndMaterialType(sellerId.id(), materialType.name())
                .orElseThrow(
                        () -> new WarehouseNotFoundException("Warehouse not found for seller %s and material type %s"
                                .formatted(sellerId.id(), materialType.name()))
                ));
    }
    

    @Override
    public WarehouseInfo getWarehouseByNumber(WarehouseNumber warehouseNumber) {
        return warehouseInfoJpaRepository
                .findByWarehouseNumber(warehouseNumber.number())
                .map(WarehouseInfoConverter::convert)
                .orElseThrow(
                        () -> new WarehouseNotFoundException("Warehouse %s not found".formatted(warehouseNumber))
                );
    }


    @Override
    public void update(WarehouseInfo warehouseInfo) {
        warehouseInfoJpaRepository.save(WarehouseInfoConverter.convert(warehouseInfo));
    }
}
