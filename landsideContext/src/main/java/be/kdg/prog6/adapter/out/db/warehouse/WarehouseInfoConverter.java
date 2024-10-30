package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.out.db.seller.SellerConverter;
import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.WarehouseInfo;
import be.kdg.prog6.domain.WarehouseNumber;

public class WarehouseInfoConverter {
    public static WarehouseInfo convert(WarehouseInfoJpaEntity warehouseInfoJpa) {
        return new WarehouseInfo(
                MaterialType.valueOf(warehouseInfoJpa.getMaterialType()),
                new Seller.SellerId(warehouseInfoJpa.getSellerId()),
                new WarehouseNumber(warehouseInfoJpa.getWarehouseNumber()),
                warehouseInfoJpa.getInitialCapacity(),
                warehouseInfoJpa.getMaxCapacity(),
                SellerConverter.fromJpa(warehouseInfoJpa.getSeller()));
    }

    public static WarehouseInfoJpaEntity convert(WarehouseInfo warehouseInfo) {
        return new WarehouseInfoJpaEntity(
               warehouseInfo.maxAmount(),
               warehouseInfo.warehouseCapacity(),
               warehouseInfo.materialType().name(),
               warehouseInfo.sellerId().id(),
               warehouseInfo.warehouseNumber().number()
        );
    }
}
