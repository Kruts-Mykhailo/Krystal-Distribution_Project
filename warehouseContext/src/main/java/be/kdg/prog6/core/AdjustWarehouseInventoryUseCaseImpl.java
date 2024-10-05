package be.kdg.prog6.core;

import be.kdg.prog6.domain.ActivityType;
import be.kdg.prog6.domain.MaterialAmount;
import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.port.in.AdjustWarehouseInventoryUseCase;
import be.kdg.prog6.port.in.PayloadDeliveredCommand;
import be.kdg.prog6.port.out.PayloadRecordSaved;
import be.kdg.prog6.port.out.ProjectWarehouseInfoPort;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import org.springframework.stereotype.Service;

@Service
public class AdjustWarehouseInventoryUseCaseImpl implements AdjustWarehouseInventoryUseCase {

    private final PayloadRecordSaved payloadRecordSaved;
    private final ProjectWarehouseInfoPort projectWarehouseInfoPort;
    private final WarehouseFoundPort warehouseFoundPort;

    public AdjustWarehouseInventoryUseCaseImpl(PayloadRecordSaved payloadRecordSaved, ProjectWarehouseInfoPort projectWarehouseInfoPort, WarehouseFoundPort warehouseFoundPort) {
        this.payloadRecordSaved = payloadRecordSaved;
        this.projectWarehouseInfoPort = projectWarehouseInfoPort;
        this.warehouseFoundPort = warehouseFoundPort;
    }

    @Override
    public void savePayloadRecord(PayloadDeliveredCommand payloadDeliveredCommand) {
        Warehouse warehouse = warehouseFoundPort.getWarehouseById(payloadDeliveredCommand.warehouseId());
        MaterialAmount warehouseMaterialAmount = warehouse.getWarehouseMaterialAmount();

        payloadRecordSaved.savePayloadRecord(
                ActivityType.DELIVERY,
                payloadDeliveredCommand.netWeight(),
                payloadDeliveredCommand.warehouseId()
        );

        projectWarehouseInfoPort.updateWarehouseCapacity(
                payloadDeliveredCommand.warehouseId(),
                warehouseMaterialAmount.amount() >= Warehouse.WAREHOUSE_MAX_CAPACITY * 0.80);
    }
}
