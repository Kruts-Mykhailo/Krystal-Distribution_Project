package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.exceptions.SellerNotFoundException;
import be.kdg.prog6.adapter.exceptions.WarehouseNotFoundException;
import be.kdg.prog6.adapter.out.db.seller.SellerJPAEntity;
import be.kdg.prog6.adapter.out.db.seller.SellerJpaRepository;
import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.WarehouseInfo;
import be.kdg.prog6.domain.WarehouseNumber;
import be.kdg.prog6.port.out.WarehouseProjectionFoundPort;
import be.kdg.prog6.port.out.WarehouseProjectionUpdatedPort;
import org.springframework.stereotype.Component;

@Component
public class WarehouseInfoDatabaseAdapter implements WarehouseProjectionFoundPort, WarehouseProjectionUpdatedPort {

    private final WarehouseInfoJpaRepository warehouseInfoJpaRepository;
    private final SellerJpaRepository sellerJpaRepository;

    public WarehouseInfoDatabaseAdapter(WarehouseInfoJpaRepository warehouseInfoJpaRepository, SellerJpaRepository sellerJpaRepository) {
        this.warehouseInfoJpaRepository = warehouseInfoJpaRepository;
        this.sellerJpaRepository = sellerJpaRepository;
    }

    @Override
    public WarehouseInfo getWarehouse(Seller.SellerId sellerId, MaterialType materialType) {
        WarehouseInfoJpaEntity warehouseInfoJpa = warehouseInfoJpaRepository
                .findBySellerIdAndMaterialTypeFetched(sellerId.id(), materialType.name())
                .orElseThrow(
                        () -> new WarehouseNotFoundException("Warehouse not found for seller %s and material type %s"
                                .formatted(sellerId.id(), materialType.name()))
                );
        SellerJPAEntity sellerJPA = sellerJpaRepository.findById(sellerId.id())
                .orElseThrow(() -> new SellerNotFoundException("Seller %s not found".formatted(sellerId.id())));
        warehouseInfoJpa.setSeller(sellerJPA);

        return WarehouseInfoConverter.convert(warehouseInfoJpa);
    }
    

    @Override
    public WarehouseInfo getWarehouseByNumber(WarehouseNumber warehouseNumber) {
        return warehouseInfoJpaRepository
                .findByWarehouseNumberFetched(warehouseNumber.number())
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
