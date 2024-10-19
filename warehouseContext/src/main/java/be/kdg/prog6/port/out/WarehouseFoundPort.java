package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.Warehouse;
import java.util.UUID;

public interface WarehouseFoundPort {
    UUID getWarehouseIdByOwnerIdAndMaterialType(Seller.SellerId id, MaterialType materialType);
    Warehouse getWarehouseById(UUID uuid);
    Warehouse getWarehouseByNumber(int number);
}
