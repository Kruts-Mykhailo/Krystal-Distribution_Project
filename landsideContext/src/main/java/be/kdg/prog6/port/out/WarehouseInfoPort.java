package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.WarehouseInfo;

import java.util.UUID;

public interface WarehouseInfoPort {
    WarehouseInfo getWarehouse(Seller.SellerId sellerId, MaterialType materialType);
    void updateWarehouse(UUID warehouseId, Double value);
}
