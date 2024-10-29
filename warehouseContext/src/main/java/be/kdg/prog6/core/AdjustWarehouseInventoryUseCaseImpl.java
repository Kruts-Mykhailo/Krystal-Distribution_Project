package be.kdg.prog6.core;

import be.kdg.prog6.domain.ActivityType;
import be.kdg.prog6.domain.PayloadActivity;
import be.kdg.prog6.domain.PayloadDeliveryEvent;
import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.events.StorageChangeEvent;
import be.kdg.prog6.port.in.AdjustInventoryCommand;
import be.kdg.prog6.port.in.AdjustWarehouseInventoryUseCase;
import be.kdg.prog6.port.out.*;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdjustWarehouseInventoryUseCaseImpl implements AdjustWarehouseInventoryUseCase {

    private final PayloadActivitySavedPort payloadActivitySavedPort;
    private final PayloadActivityFoundPort payloadActivityFoundPort;
    private final PayloadActivityUpdatedPort payloadActivityUpdatedPort;
    private final ProjectWarehouseInfoPort projectWarehouseInfoPort;
    private final InvoicingStorageRecordUpdatedPort invoicingStorageRecordUpdatedPort;
    private final WarehouseFoundPort warehouseFoundPort;
    private final Logger logger = LoggerFactory.getLogger(AdjustWarehouseInventoryUseCaseImpl.class);

    public AdjustWarehouseInventoryUseCaseImpl(PayloadActivitySavedPort payloadActivitySavedPort, PayloadActivityFoundPort payloadActivityFoundPort, PayloadActivityUpdatedPort payloadActivityUpdatedPort, ProjectWarehouseInfoPort projectWarehouseInfoPort, InvoicingStorageRecordUpdatedPort invoicingStorageRecordUpdatedPort, WarehouseFoundPort warehouseFoundPort) {
        this.payloadActivitySavedPort = payloadActivitySavedPort;
        this.payloadActivityFoundPort = payloadActivityFoundPort;
        this.payloadActivityUpdatedPort = payloadActivityUpdatedPort;
        this.projectWarehouseInfoPort = projectWarehouseInfoPort;
        this.invoicingStorageRecordUpdatedPort = invoicingStorageRecordUpdatedPort;
        this.warehouseFoundPort = warehouseFoundPort;
    }

    @Override
    @Transactional
    public void savePayloadRecord(AdjustInventoryCommand command) {
        Warehouse warehouse = warehouseFoundPort.getWarehouseByNumber(command.warehouseNumber());

        Optional<PayloadActivity> activity = payloadActivityFoundPort
                .getActivityByWarehouseAndArrivalTimeAndAmount(
                        command.warehouseNumber(),
                        command.sendTime(),
                        0.0);

        if (activity.isPresent()) {
            PayloadActivity payloadActivity = activity.get();

            payloadActivityUpdatedPort.updateWeight(payloadActivity,
                    warehouse.getWarehouseNumber(),
                    command.netWeight());

            invoicingStorageRecordUpdatedPort.send(
                    new StorageChangeEvent(
                            warehouse.getOwnerId().id(),
                            command.netWeight(),
                            command.sendTime(),
                            warehouse.getMaterialType().name()));

        } else {
           payloadActivitySavedPort.savePayloadActivity(
                   new PayloadDeliveryEvent(
                           command.netWeight(),
                           command.sendTime()),
                   command.warehouseNumber());
        }

        projectWarehouseInfoPort.projectWarehouseCapacity(
                warehouse.getWarehouseNumber(),
                command.netWeight(),
                ActivityType.DELIVERY
        );

        logger.info("%s warehouse received a payload of %.2f tons".formatted(
                warehouse.getWarehouseNumber().number(),
                command.netWeight())
        );
    }
}
