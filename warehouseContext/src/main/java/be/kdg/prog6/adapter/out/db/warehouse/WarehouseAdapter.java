package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.exceptions.WarehouseNotFoundException;
import be.kdg.prog6.adapter.out.db.payloadActivity.PayloadActivityJpaEntity;
import be.kdg.prog6.adapter.out.db.payloadActivity.PayloadActivityJpaRepository;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.out.PayloadRecordSaved;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class WarehouseAdapter implements WarehouseFoundPort, PayloadRecordSaved {

    private final WarehouseJpaRepository warehouseJpaRepository;
    private final PayloadActivityJpaRepository payloadActivityJpaRepository;

    public WarehouseAdapter(WarehouseJpaRepository warehouseJpaRepository, PayloadActivityJpaRepository payloadActivityJpaRepository) {
        this.warehouseJpaRepository = warehouseJpaRepository;
        this.payloadActivityJpaRepository = payloadActivityJpaRepository;
    }

    @Override
    public void savePayloadRecord(ActivityType activityType, Double amount, UUID warehouseId, LocalDateTime timestamp) {
        PayloadActivityJpaEntity payloadActivityJpaEntity = new PayloadActivityJpaEntity(
                activityType.name(),
                amount,
                timestamp,
                new WarehouseJpaEntity(warehouseId)
        );
        payloadActivityJpaRepository.save(payloadActivityJpaEntity);
    }

    @Override
    public Warehouse getWarehouseById(UUID uuid) {
        WarehouseJpaEntity warehouseJpaEntity = warehouseJpaRepository.findByWarehouseIdFetched(uuid)
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse with id %s not found.".formatted(uuid)));
        List<PayloadActivity> payloadActivityJpaEntities = warehouseJpaEntity.getPayloadActivityJpaEntities() == null ?
                new ArrayList<>() : warehouseJpaEntity.getPayloadActivityJpaEntities()
                .stream()
                .map(this::toPayloadActivity)
                .collect(Collectors.toList());

        return toWarehouse(warehouseJpaEntity, payloadActivityJpaEntities);
    }

    private PayloadActivity toPayloadActivity(PayloadActivityJpaEntity payloadActivityJpaEntity) {
        if (ActivityType.valueOf(payloadActivityJpaEntity.getActivityType()) == ActivityType.DELIVERY) {
            return new PayloadDeliveryEvent(payloadActivityJpaEntity.getAmount(), payloadActivityJpaEntity.getRecordTime());
        }
        return new PayloadPurchaseEvent(payloadActivityJpaEntity.getAmount(), payloadActivityJpaEntity.getRecordTime());
    }

    private Warehouse toWarehouse(WarehouseJpaEntity warehouse, List<PayloadActivity> payloadActivities) {
        return new Warehouse(
                warehouse.getWarehouseId(),
                warehouse.getWarehouseNumber(),
                warehouse.getOwnerId(),
                MaterialType.valueOf(warehouse.getMaterialType()),
                payloadActivities
        );
    }


}
