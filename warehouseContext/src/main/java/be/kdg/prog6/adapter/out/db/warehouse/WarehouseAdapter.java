package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.exceptions.WarehouseNotFoundException;
import be.kdg.prog6.adapter.out.db.payloadActivity.PayloadActivityJpaEntity;
import be.kdg.prog6.adapter.out.db.payloadActivity.PayloadActivityJpaRepository;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import be.kdg.prog6.port.out.WarehouseUpdatedPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WarehouseAdapter implements WarehouseFoundPort, WarehouseUpdatedPort {

    private final WarehouseJpaRepository warehouseJpaRepository;
    private final PayloadActivityJpaRepository payloadActivityJpaRepository;

    public WarehouseAdapter(WarehouseJpaRepository warehouseJpaRepository, PayloadActivityJpaRepository payloadActivityJpaRepository) {
        this.warehouseJpaRepository = warehouseJpaRepository;
        this.payloadActivityJpaRepository = payloadActivityJpaRepository;
    }

    @Override
    public Warehouse getWarehouseByOwnerIdAndMaterialType(Seller.SellerId id, MaterialType materialType) {
        return warehouseJpaRepository
                .findBySellerIdAndMaterialType(id.id(), materialType.name())
                .map(WarehouseConverter::fromJpa)
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found"));
    }

    @Override
    public Warehouse getWarehouseByNumber(WarehouseNumber warehouseNumber) {
        WarehouseJpaEntity warehouseJpaEntity = warehouseJpaRepository.findByWarehouseNumberFetched(warehouseNumber.number())
                .orElseThrow(() -> new WarehouseNotFoundException(
                        "Warehouse with number %s not found.".formatted(warehouseNumber.number())));

        List<PayloadActivityJpaEntity> payloadActivities = payloadActivityJpaRepository
                .findAllByRecordTimeAfterAndWarehouseWarehouseNumber(warehouseJpaEntity.getSnapshotAt(), warehouseNumber.number());

        warehouseJpaEntity.setPayloadActivityJpaEntities(payloadActivities);
        return WarehouseConverter.fromJpaFetched(warehouseJpaEntity);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseJpaRepository.findAllFetchedSeller()
                .stream()
                .map(w -> {
                    List<PayloadActivityJpaEntity> payloadActivities = payloadActivityJpaRepository
                            .findAllByRecordTimeAfterAndWarehouseWarehouseNumber(
                                    w.getSnapshotAt(), w.getWarehouseNumber());
                    w.setPayloadActivityJpaEntities(payloadActivities);
                    return WarehouseConverter.fromJpaFetched(w);
                })
                .collect(Collectors.toList());
    }

    @Override
    public void updateSnapshot(Warehouse warehouse) {
        WarehouseJpaEntity warehouseJpaEntity = warehouseJpaRepository.findById(warehouse.getWarehouseNumber().number())
                .orElseThrow(() -> new WarehouseNotFoundException(
                        "Warehouse with number %s not found.".formatted(warehouse.getWarehouseNumber().number())));
        warehouseJpaEntity.setSnapshotAt(warehouse.getCurrentAmount().calculationDateTime());
        warehouseJpaEntity.setSnapshotAmount(warehouse.getCurrentAmount().amount());
        warehouseJpaRepository.save(warehouseJpaEntity);

    }
}
