package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.in.exceptions.WarehouseNotFoundException;
import be.kdg.prog6.adapter.out.db.payload.PayloadConverter;
import be.kdg.prog6.adapter.out.db.payload.PayloadJpaEntity;
import be.kdg.prog6.adapter.out.db.payload.PayloadJpaEntityRepository;
import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Payload;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.port.out.UpdateWarehouseStoragePort;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class WarehouseAdapter implements WarehouseFoundPort, UpdateWarehouseStoragePort {

    private final WarehouseJpaEntityRepository warehouseJpaEntityRepository;
    private final PayloadJpaEntityRepository payloadJpaEntityRepository;

    public WarehouseAdapter(WarehouseJpaEntityRepository warehouseJpaEntityRepository, PayloadJpaEntityRepository payloadJpaEntityRepository) {
        this.warehouseJpaEntityRepository = warehouseJpaEntityRepository;
        this.payloadJpaEntityRepository = payloadJpaEntityRepository;
    }

    @Override
    public void update(Warehouse warehouse) {
        WarehouseJpaEntity warehouseDB = warehouseJpaEntityRepository
                .findBySellerIdAndMaterialTypeFetched(warehouse.getSellerId().uuid(), warehouse.getMaterialType().name())
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found"));
        List<PayloadJpaEntity> existingJpaPayloads = warehouseDB.getPayloads();
        List<Payload> existingPayloads = existingJpaPayloads.stream().map(PayloadConverter::toPayload).toList();
        List<Payload> newPayloads = warehouse.getPayloads();

       // Remove difference
        List<PayloadJpaEntity> payloadsToDelete = existingJpaPayloads.stream()
                .filter(existingPayload -> newPayloads.stream()
                        .noneMatch(newPayload ->
                                existingPayload.getTons().equals(newPayload.getTons()) &&
                                        existingPayload.getArrivalDate().equals(newPayload.getDeliveryDate())))
                .collect(Collectors.toList());

        payloadJpaEntityRepository.deleteAllInBatch(payloadsToDelete);

        // Add new and updated
        List<PayloadJpaEntity> payloadsToCreate = newPayloads.stream()
                .filter(newPayload -> existingPayloads.stream()
                        .noneMatch(existingPayload -> existingPayload.getDeliveryDate().equals(newPayload.getDeliveryDate()) &&
                                existingPayload.getTons().equals(newPayload.getTons())))
                .map(p -> PayloadConverter.toJpa(p, warehouseDB))
                .collect(Collectors.toList());

        payloadJpaEntityRepository.saveAll(payloadsToCreate);

    }

    @Override
    public Warehouse getBySellerIdAndMaterialType(Seller.SellerId sellerId, MaterialType materialType) {
        return WarehouseConverter.fromJpa(warehouseJpaEntityRepository.findBySellerIdAndMaterialTypeFetched(sellerId.uuid(), materialType.name())
                .orElseThrow(() -> new WarehouseNotFoundException("Warehouse not found")));
    }

    @Override
    public List<Warehouse> getWarehousesBySellerId(Seller.SellerId sellerId) {
        return warehouseJpaEntityRepository.findAllBySellerIdFetched(sellerId.uuid())
                .stream()
                .map(WarehouseConverter::fromJpa)
                .collect(Collectors.toList());
    }



}
