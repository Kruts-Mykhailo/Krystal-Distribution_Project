package be.kdg.prog6.core;

import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.domain.WarehouseNumber;
import be.kdg.prog6.port.in.RecordWarehouseStateUseCase;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import be.kdg.prog6.port.out.WarehouseUpdatedPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class RecordWarehouseStateUseCaseImpl implements RecordWarehouseStateUseCase {

    private final WarehouseUpdatedPort warehouseUpdatedPort;
    private final WarehouseFoundPort warehouseFoundPort;
    private final Logger logger = LoggerFactory.getLogger(RecordWarehouseStateUseCaseImpl.class);

    public RecordWarehouseStateUseCaseImpl(WarehouseUpdatedPort warehouseUpdatedPort, WarehouseFoundPort warehouseFoundPort) {
        this.warehouseUpdatedPort = warehouseUpdatedPort;
        this.warehouseFoundPort = warehouseFoundPort;
    }

    @Override
    public void snapshot(WarehouseNumber warehouseNumber) {
        Warehouse warehouse = warehouseFoundPort.getWarehouseByNumberAfterSnapshot(warehouseNumber);
        warehouse.snapshot();
        warehouseUpdatedPort.updateSnapshot(warehouse);
        logger.info("Record snapshot for warehouse {}", warehouseNumber);
    }
}
