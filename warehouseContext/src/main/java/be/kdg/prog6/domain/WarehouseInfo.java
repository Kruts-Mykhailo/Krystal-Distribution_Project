package be.kdg.prog6.domain;

public record WarehouseInfo(int warehouseNumber, Seller.SellerId sellerId, Double payloadAmount, MaterialType materialType) {
}
