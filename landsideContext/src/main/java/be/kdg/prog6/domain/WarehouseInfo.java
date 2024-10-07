package be.kdg.prog6.domain;

import java.util.Objects;
import java.util.UUID;

public record WarehouseInfo(MaterialType materialType, Seller.SellerId sellerId, UUID warehouseId, int warehouseNumber, Double warehouseCapacity, Double maxAmount) {

    public WarehouseInfo {
        Objects.requireNonNull(sellerId);
        Objects.requireNonNull(warehouseId);
    }

    public boolean isFullCapacity() {
        return warehouseCapacity >= maxAmount * 0.80;
    }
}
