package be.kdg.prog6.domain;

public record WarehouseInfo(WarehouseNumber warehouseNumber, Seller.SellerId sellerId, Double payloadAmount, MaterialType materialType) {
}
