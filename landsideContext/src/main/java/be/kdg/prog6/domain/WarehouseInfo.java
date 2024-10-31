package be.kdg.prog6.domain;

import be.kdg.prog6.adapter.exceptions.WarehouseHasFullCapacityException;

import java.util.Objects;
import java.util.UUID;

public final class WarehouseInfo {
    private final MaterialType materialType;
    private final Seller.SellerId sellerId;
    private final WarehouseNumber warehouseNumber;
    private Double warehouseCapacity;
    private final Double maxAmount;
    private final Seller seller;


    public WarehouseInfo(MaterialType materialType, Seller.SellerId sellerId, WarehouseNumber warehouseNumber, Double warehouseCapacity, Double maxAmount, Seller seller) {
        this.materialType = materialType;
        this.sellerId = sellerId;
        this.warehouseNumber = warehouseNumber;
        this.warehouseCapacity = warehouseCapacity;
        this.maxAmount = maxAmount;
        this.seller = seller;
    }

    public void updateCapacity(Double amount, OperationType operationType) {
        if (operationType.equals(OperationType.DELIVERY)) {
            warehouseCapacity += amount;
        } else {
            warehouseCapacity -= amount;
        }

    }


    public void isFullCapacity() {
        if (warehouseCapacity >= maxAmount * 0.80) {
            throw new WarehouseHasFullCapacityException(
                    String.format("Warehouse %s has full capacity", this.warehouseNumber.number()));

        }

    }

    public MaterialType materialType() {
        return materialType;
    }

    public Seller.SellerId sellerId() {
        return sellerId;
    }

    public WarehouseNumber warehouseNumber() {
        return warehouseNumber;
    }

    public Double warehouseCapacity() {
        return warehouseCapacity;
    }

    public Double maxAmount() {
        return maxAmount;
    }

    public Seller getSeller() {
        return seller;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (WarehouseInfo) obj;
        return Objects.equals(this.materialType, that.materialType) &&
                Objects.equals(this.sellerId, that.sellerId) &&
                this.warehouseNumber == that.warehouseNumber &&
                Objects.equals(this.warehouseCapacity, that.warehouseCapacity) &&
                Objects.equals(this.maxAmount, that.maxAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialType, sellerId, warehouseNumber, warehouseCapacity, maxAmount);
    }

    @Override
    public String toString() {
        return "WarehouseInfo[" +
                "materialType=" + materialType + ", " +
                "sellerId=" + sellerId + ", " +
                "warehouseNumber=" + warehouseNumber + ", " +
                "warehouseCapacity=" + warehouseCapacity + ", " +
                "maxAmount=" + maxAmount + ']';
    }

}
