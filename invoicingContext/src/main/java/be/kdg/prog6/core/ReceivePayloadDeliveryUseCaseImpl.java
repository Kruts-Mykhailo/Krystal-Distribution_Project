package be.kdg.prog6.core;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.events.StorageChangeEvent;
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
    public void addPayload(StorageChangeEvent storageChangeEvent) {
        Warehouse warehouse = warehouseFoundPort.getBySellerIdAndMaterialType(
                new Seller.SellerId(storageChangeEvent.sellerId()),
                MaterialType.valueOf(storageChangeEvent.materialType()));
        warehouse.addPayload(storageChangeEvent.tons(), storageChangeEvent.arrivalTime());
        warehouseStoragePort.update(warehouse);

        log.info("Seller %s received %s %.2f tons".formatted(
                storageChangeEvent.sellerId(),
                storageChangeEvent.materialType(),
                storageChangeEvent.tons()));
    }
}
