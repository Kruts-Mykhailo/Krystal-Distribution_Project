package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Payload;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.domain.materialPricing.MaterialPricingFactory;

import java.util.stream.Collectors;

public class WarehouseConverter {
    public static Warehouse fromJpa(WarehouseJpaEntity warehouseJpaEntity) {
        return new Warehouse(
                MaterialType.valueOf(warehouseJpaEntity.getMaterialType()),
                warehouseJpaEntity.getPayloads()
                        .stream()
                        .map(p -> new Payload(
                                p.getArrivalDate(),
                                p.getTons()
                        ))
                        .collect(Collectors.toList()),
                new Seller.SellerId(warehouseJpaEntity.getSellerId()),
                MaterialPricingFactory.createMaterialPricing(MaterialType.valueOf(warehouseJpaEntity.getMaterialType()))
        );
    }
}
