package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.domain.Warehouse;

import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Collectors;

public record WarehouseInfoDTO (String warehouseNumber, UUID sellerId, Double payloadAmount, Double maxCapacity, String materialType){
    public static WarehouseInfoDTO from (Warehouse warehouse) {
        return new WarehouseInfoDTO(
                warehouse.getWarehouseNumber().number(),
                warehouse.getSeller().getSellerId().id(),
                warehouse.getWarehouseMaterialAmount().amount(),
                warehouse.getMaxCapacity(),
                Arrays.stream(warehouse.getMaterialType().name().toLowerCase().split("_"))
                        .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                        .collect(Collectors.joining(" "))
        );
    }
}
