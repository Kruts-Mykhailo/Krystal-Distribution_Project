package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.domain.WarehouseNumber;

import java.util.List;

public interface WarehouseFoundPort {
    Warehouse getWarehouseByOwnerIdAndMaterialType(Seller.SellerId id, MaterialType materialType);
    Warehouse getWarehouseByNumberAfterSnapshot(WarehouseNumber number);
    List<Warehouse> getAllWarehousesAfterSnapshot();
}
