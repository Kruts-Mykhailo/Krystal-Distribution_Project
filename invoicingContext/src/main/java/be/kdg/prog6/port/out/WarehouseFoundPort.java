package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.Warehouse;

import java.util.List;

public interface WarehouseFoundPort {
    Warehouse getBySellerIdAndMaterialType(Seller.SellerId sellerId, MaterialType materialType);
    List<Warehouse> getWarehousesBySellerId(Seller.SellerId sellerId);
}
