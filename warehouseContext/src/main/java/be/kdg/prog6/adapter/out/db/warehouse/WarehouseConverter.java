package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.PayloadActivity;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.Warehouse;

import java.util.List;

public class WarehouseConverter {
    public static Warehouse toWarehouse(WarehouseJpaEntity warehouse, List<PayloadActivity> payloadActivities) {
        return new Warehouse(
                warehouse.getWarehouseId(),
                warehouse.getWarehouseNumber(),
                new Seller.SellerId(warehouse.getOwnerId()),
                MaterialType.valueOf(warehouse.getMaterialType()),
                payloadActivities
        );
    }

}
