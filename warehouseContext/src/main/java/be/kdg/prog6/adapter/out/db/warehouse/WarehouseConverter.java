package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.out.db.seller.SellerConverter;
import be.kdg.prog6.domain.*;

import java.util.ArrayList;
import java.util.List;

public class WarehouseConverter {
    public static Warehouse toWarehouseFetched(WarehouseJpaEntity warehouse, List<PayloadActivity> payloadActivities) {
        return new Warehouse(
                new WarehouseNumber(warehouse.getWarehouseNumber()),
                SellerConverter.fromJpa(warehouse.getSeller()),
                MaterialType.valueOf(warehouse.getMaterialType()),
                payloadActivities
        );
    }
    public static Warehouse toWarehouse(WarehouseJpaEntity warehouse) {
        return new Warehouse(
                new WarehouseNumber(warehouse.getWarehouseNumber()),
                SellerConverter.fromJpa(warehouse.getSeller()),
                MaterialType.valueOf(warehouse.getMaterialType()),
                new ArrayList<>()
        );
    }



}
