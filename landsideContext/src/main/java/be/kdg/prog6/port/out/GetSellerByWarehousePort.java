package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.Seller;

import java.util.UUID;

public interface GetSellerByWarehousePort {
    Seller.SellerId getSellerByWarehouseId(UUID warehouseId);
}
