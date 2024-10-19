package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.WarehouseInfo;

public class WarehouseInfoConverter {
    public static WarehouseInfo convert(WarehouseInfoJpaEntity warehouseInfoJpa) {
        return new WarehouseInfo(
                MaterialType.valueOf(warehouseInfoJpa.getMaterialType()),
                new Seller.SellerId(warehouseInfoJpa.getSellerId()),
                warehouseInfoJpa.getWarehouseId(),
                warehouseInfoJpa.getWarehouseNumber(),
                warehouseInfoJpa.getInitialCapacity(),
                warehouseInfoJpa.getMaxCapacity());
    }

    public static WarehouseInfoJpaEntity convert(WarehouseInfo warehouseInfo) {
        return new WarehouseInfoJpaEntity(
               warehouseInfo.maxAmount(),
               warehouseInfo.warehouseCapacity(),
               warehouseInfo.materialType().name(),
               warehouseInfo.sellerId().id(),
               warehouseInfo.warehouseNumber(),
               warehouseInfo.warehouseId()
        );
    }
}
