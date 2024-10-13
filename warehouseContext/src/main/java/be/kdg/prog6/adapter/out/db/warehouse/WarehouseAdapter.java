package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.exceptions.WarehouseNotFoundException;
import be.kdg.prog6.adapter.out.db.payloadActivity.PayloadActivityJpaEntity;
import be.kdg.prog6.adapter.out.db.payloadActivity.PayloadActivityJpaRepository;
import be.kdg.prog6.adapter.out.db.payloadActivity.PayloadConverter;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.out.PayloadCommand;
import be.kdg.prog6.port.out.PayloadRecordSavedPort;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class WarehouseAdapter implements WarehouseFoundPort {

    private final WarehouseJpaRepository warehouseJpaRepository;

    public WarehouseAdapter(WarehouseJpaRepository warehouseJpaRepository, PayloadActivityJpaRepository payloadActivityJpaRepository) {
        this.warehouseJpaRepository = warehouseJpaRepository;
    }

    @Override
    public UUID getWarehouseIdByOwnerIdAndMaterialType(Seller.SellerId id, MaterialType materialType) {
        return warehouseJpaRepository.findByOwnerIdAndMaterialType(id.id(), materialType.name())
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found")).getWarehouseId();
    }

    @Override
    public Warehouse getWarehouseById(UUID uuid) {
        WarehouseJpaEntity warehouseJpaEntity = warehouseJpaRepository.findByWarehouseIdFetched(uuid)
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse with id %s not found.".formatted(uuid)));
        List<PayloadActivity> payloadActivityJpaEntities = PayloadConverter
                .toPayloadActivities(warehouseJpaEntity.getPayloadActivityJpaEntities());
        return WarehouseConverter.toWarehouse(warehouseJpaEntity, payloadActivityJpaEntities);
    }

    @Override
    public Warehouse getWarehouseByNumber(int number) {
        WarehouseJpaEntity warehouseJpaEntity = warehouseJpaRepository.findByWarehouseNumberFetched(number)
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse with number %d not found.".formatted(number)));
        List<PayloadActivity> payloadActivityJpaEntities = PayloadConverter
                .toPayloadActivities(warehouseJpaEntity.getPayloadActivityJpaEntities());
        return WarehouseConverter.toWarehouse(warehouseJpaEntity, payloadActivityJpaEntities);
    }


}
