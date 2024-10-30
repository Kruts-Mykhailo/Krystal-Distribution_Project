package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.exceptions.WarehouseNotFoundException;
import be.kdg.prog6.adapter.out.db.payloadActivity.PayloadActivityJpaRepository;
import be.kdg.prog6.adapter.out.db.payloadActivity.PayloadConverter;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import org.springframework.stereotype.Component;

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
    public Warehouse getWarehouseByOwnerIdAndMaterialType(Seller.SellerId id, MaterialType materialType) {
        return warehouseJpaRepository
                .findBySellerIdAndMaterialType(id.id(), materialType.name())
                .map(WarehouseConverter::toWarehouse)
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found"));
    }

    @Override
    public Warehouse getWarehouseByNumber(WarehouseNumber warehouseNumber) {
        WarehouseJpaEntity warehouseJpaEntity = warehouseJpaRepository.findByWarehouseNumberFetched(warehouseNumber.number())
                .orElseThrow(() -> new WarehouseNotFoundException(
                        "Warehouse with number %s not found.".formatted(warehouseNumber.number())));

        List<PayloadActivity> payloadActivityJpaEntities = PayloadConverter
                .toPayloadActivities(warehouseJpaEntity.getPayloadActivityJpaEntities());
        return WarehouseConverter.toWarehouseFetched(warehouseJpaEntity, payloadActivityJpaEntities);
    }

    @Override
    public List<Warehouse> getAllWarehouses() {
        var list = warehouseJpaRepository.findAllFetched();
        return list
                .stream()
                .map(w -> WarehouseConverter.toWarehouseFetched(
                        w,
                        PayloadConverter.toPayloadActivities(w.getPayloadActivityJpaEntities())
                ))
                .collect(Collectors.toList());
    }


}
