package be.kdg.prog6.domain;

import java.util.Objects;
import java.util.UUID;

public final class WarehouseInfo {
    private final MaterialType materialType;
    private final Seller.SellerId sellerId;
    private final UUID warehouseId;
    private final int warehouseNumber;
    private Double warehouseCapacity;
    private final Double maxAmount;


    public WarehouseInfo(MaterialType materialType, Seller.SellerId sellerId, UUID warehouseId, int warehouseNumber, Double warehouseCapacity, Double maxAmount) {
        Objects.requireNonNull(sellerId);
        Objects.requireNonNull(warehouseId);
        this.materialType = materialType;
        this.sellerId = sellerId;
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.warehouseCapacity = warehouseCapacity;
        this.maxAmount = maxAmount;
    }

    public void updateCapacity(Double amount, OperationType operationType) {
        if (operationType.equals(OperationType.DELIVERY)) {
            warehouseCapacity += amount;
        } else {
            warehouseCapacity -= amount;
        }

    }


    public boolean isFullCapacity() {
        return warehouseCapacity >= maxAmount * 0.80;
    }

    public MaterialType materialType() {
        return materialType;
    }

    public Seller.SellerId sellerId() {
        return sellerId;
    }

    public UUID warehouseId() {
        return warehouseId;
    }

    public int warehouseNumber() {
        return warehouseNumber;
    }

    public Double warehouseCapacity() {
        return warehouseCapacity;
    }

    public Double maxAmount() {
        return maxAmount;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (WarehouseInfo) obj;
        return Objects.equals(this.materialType, that.materialType) &&
                Objects.equals(this.sellerId, that.sellerId) &&
                Objects.equals(this.warehouseId, that.warehouseId) &&
                this.warehouseNumber == that.warehouseNumber &&
                Objects.equals(this.warehouseCapacity, that.warehouseCapacity) &&
                Objects.equals(this.maxAmount, that.maxAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialType, sellerId, warehouseId, warehouseNumber, warehouseCapacity, maxAmount);
    }

    @Override
    public String toString() {
        return "WarehouseInfo[" +
                "materialType=" + materialType + ", " +
                "sellerId=" + sellerId + ", " +
                "warehouseId=" + warehouseId + ", " +
                "warehouseNumber=" + warehouseNumber + ", " +
                "warehouseCapacity=" + warehouseCapacity + ", " +
                "maxAmount=" + maxAmount + ']';
    }

}
