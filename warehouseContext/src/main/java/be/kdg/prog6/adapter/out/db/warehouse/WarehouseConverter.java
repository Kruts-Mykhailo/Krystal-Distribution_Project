package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.out.db.payloadActivity.PayloadConverter;
import be.kdg.prog6.adapter.out.db.seller.SellerConverter;
import be.kdg.prog6.domain.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class WarehouseConverter {
    public static Warehouse fromJpaFetched(WarehouseJpaEntity warehouse) {
        return new Warehouse(
                new WarehouseNumber(warehouse.getWarehouseNumber()),
                SellerConverter.fromJpa(warehouse.getSeller()),
                MaterialType.valueOf(warehouse.getMaterialType()),
                warehouse.getPayloadActivityJpaEntities().stream().map(PayloadConverter::fromJpa).collect(Collectors.toList()),
                new MaterialAmount(warehouse.getSnapshotAmount(), warehouse.getSnapshotAt())
        );
    }

    public static Warehouse fromJpa(WarehouseJpaEntity warehouse) {
        return new Warehouse(
                new WarehouseNumber(warehouse.getWarehouseNumber()),
                SellerConverter.fromJpa(warehouse.getSeller()),
                MaterialType.valueOf(warehouse.getMaterialType()),
                new ArrayList<>(),
                new MaterialAmount(warehouse.getSnapshotAmount(), warehouse.getSnapshotAt())
        );
    }



}
