package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.WarehouseInfo;
import be.kdg.prog6.domain.WarehouseNumber;

import java.util.UUID;

public interface WarehouseProjectionFoundPort {
    WarehouseInfo getWarehouse(Seller.SellerId sellerId, MaterialType materialType);
    WarehouseInfo getWarehouseByNumber(WarehouseNumber warehouseNumber);
}
