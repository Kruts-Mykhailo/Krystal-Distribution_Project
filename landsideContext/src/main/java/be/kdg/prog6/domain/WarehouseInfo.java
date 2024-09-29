package be.kdg.prog6.domain;

import java.util.Objects;
import java.util.UUID;

public record WarehouseInfo(MaterialType materialType, Seller.SellerId sellerId, UUID warehouseId, int warehouseNumber, boolean fullCapacity) {

    public WarehouseInfo {
        Objects.requireNonNull(sellerId);
        Objects.requireNonNull(warehouseId);
        Objects.requireNonNull(warehouseNumber);
    }
}
