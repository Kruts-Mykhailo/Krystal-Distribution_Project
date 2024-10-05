package be.kdg.prog6.core;

import be.kdg.prog6.domain.ActivityType;
import be.kdg.prog6.domain.MaterialAmount;
import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.port.in.AdjustWarehouseInventoryUseCase;
import be.kdg.prog6.port.in.PayloadDeliveredCommand;
import be.kdg.prog6.port.out.PayloadRecordSaved;
import be.kdg.prog6.port.out.ProjectWarehouseInfoPort;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AdjustWarehouseInventoryUseCaseImpl implements AdjustWarehouseInventoryUseCase {

    private final PayloadRecordSaved payloadRecordSaved;
    private final ProjectWarehouseInfoPort projectWarehouseInfoPort;
    private final WarehouseFoundPort warehouseFoundPort;
    private final Logger logger = LoggerFactory.getLogger(AdjustWarehouseInventoryUseCaseImpl.class);

    public AdjustWarehouseInventoryUseCaseImpl(PayloadRecordSaved payloadRecordSaved, ProjectWarehouseInfoPort projectWarehouseInfoPort, WarehouseFoundPort warehouseFoundPort) {
        this.payloadRecordSaved = payloadRecordSaved;
        this.projectWarehouseInfoPort = projectWarehouseInfoPort;
        this.warehouseFoundPort = warehouseFoundPort;
    }

    @Override
    @Transactional
    public void savePayloadRecord(PayloadDeliveredCommand payloadDeliveredCommand) {
        Warehouse warehouse = warehouseFoundPort.getWarehouseById(payloadDeliveredCommand.warehouseId());

        payloadRecordSaved.savePayloadRecord(
                ActivityType.DELIVERY,
                payloadDeliveredCommand.netWeight(),
                payloadDeliveredCommand.warehouseId(),
                payloadDeliveredCommand.sendTime()
        );

        projectWarehouseInfoPort.updateWarehouseCapacity(
                payloadDeliveredCommand.warehouseId(),
                warehouse.isWarehouseAtFullCapacity());

        logger.info("%d warehouse received a payload of %.2f tons".formatted(
                warehouse.getWarehouseNumber(),
                payloadDeliveredCommand.netWeight())
        );
    }
}
