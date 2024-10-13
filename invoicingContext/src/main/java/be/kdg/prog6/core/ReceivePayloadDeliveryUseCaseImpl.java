package be.kdg.prog6.core;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.events.StorageChangedEvent;
import be.kdg.prog6.port.in.ReceivePayloadDeliveryUseCase;
import be.kdg.prog6.port.out.UpdateWarehouseStoragePort;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class ReceivePayloadDeliveryUseCaseImpl implements ReceivePayloadDeliveryUseCase {

    private static final Logger log = LoggerFactory.getLogger(ReceivePayloadDeliveryUseCaseImpl.class);
    private final UpdateWarehouseStoragePort warehouseStoragePort;
    private final WarehouseFoundPort warehouseFoundPort;

    public ReceivePayloadDeliveryUseCaseImpl(UpdateWarehouseStoragePort warehouseStoragePort, WarehouseFoundPort warehouseFoundPort) {
        this.warehouseStoragePort = warehouseStoragePort;
        this.warehouseFoundPort = warehouseFoundPort;
    }

    @Override
    @Transactional
    public void addPayload(StorageChangedEvent storageChangedEvent) {
        Warehouse warehouse = warehouseFoundPort.getBySellerIdAndMaterialType(
                new Seller.SellerId(storageChangedEvent.sellerId()),
                MaterialType.valueOf(storageChangedEvent.materialType()));
        warehouse.addPayload(storageChangedEvent.tons(), storageChangedEvent.arrivalTime());
        warehouseStoragePort.update(warehouse);

        log.info("Seller %s received %s %.2f tons".formatted(
                storageChangedEvent.sellerId(),
                storageChangedEvent.materialType(),
                storageChangedEvent.tons()));
    }
}
