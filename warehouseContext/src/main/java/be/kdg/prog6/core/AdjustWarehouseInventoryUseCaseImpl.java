package be.kdg.prog6.core;

import be.kdg.prog6.domain.ActivityType;
import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.events.StorageChangeEvent;
import be.kdg.prog6.port.in.AdjustWarehouseInventoryUseCase;
import be.kdg.prog6.port.out.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AdjustWarehouseInventoryUseCaseImpl implements AdjustWarehouseInventoryUseCase {

    private final PayloadRecordSavedPort payloadRecordSaved;
    private final ProjectWarehouseInfoPort projectWarehouseInfoPort;
    private final InvoicingStorageRecordUpdatedPort invoicingStorageRecordUpdatedPort;
    private final WarehouseFoundPort warehouseFoundPort;
    private final Logger logger = LoggerFactory.getLogger(AdjustWarehouseInventoryUseCaseImpl.class);

    public AdjustWarehouseInventoryUseCaseImpl(PayloadRecordSavedPort payloadRecordSaved, ProjectWarehouseInfoPort projectWarehouseInfoPort, InvoicingStorageRecordUpdatedPort invoicingStorageRecordUpdatedPort, WarehouseFoundPort warehouseFoundPort) {
        this.payloadRecordSaved = payloadRecordSaved;
        this.projectWarehouseInfoPort = projectWarehouseInfoPort;
        this.invoicingStorageRecordUpdatedPort = invoicingStorageRecordUpdatedPort;
        this.warehouseFoundPort = warehouseFoundPort;
    }

    @Override
    @Transactional
    public void savePayloadRecord(UUID warehouseId, LocalDateTime sendTime, Double netWeight) {
        payloadRecordSaved.saveOrUpdatePayloadRecord(new PayloadCommand(
                ActivityType.DELIVERY,
                netWeight,
                warehouseId,
                sendTime
        ));


        Warehouse warehouse = warehouseFoundPort.getWarehouseById(warehouseId);

        if (netWeight != 0.0) {
            invoicingStorageRecordUpdatedPort.send(
                    new StorageChangeEvent(
                        warehouse.getOwnerId().id(),
                        netWeight,
                        sendTime,
                        warehouse.getMaterialType().name()));
        }

        projectWarehouseInfoPort.projectWarehouseCapacity(
                warehouseId,
                netWeight,
                ActivityType.DELIVERY
        );

        logger.info("%d warehouse received a payload of %.2f tons".formatted(
                warehouse.getWarehouseNumber(),
                netWeight)
        );
    }
}
