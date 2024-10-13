package be.kdg.prog6.core;

import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.domain.WarehouseInfo;
import be.kdg.prog6.port.in.GetWarehouseInfoUseCase;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class GetWarehouseInfoUseCaseImpl implements GetWarehouseInfoUseCase {

    private final WarehouseFoundPort warehouseFoundPort;

    public GetWarehouseInfoUseCaseImpl(WarehouseFoundPort warehouseFoundPort) {
        this.warehouseFoundPort = warehouseFoundPort;
    }

    @Override
    @Transactional
    public WarehouseInfo getWarehouseInfo(int warehouseNumber) {
        Warehouse warehouse = warehouseFoundPort.getWarehouseByNumber(warehouseNumber);
        return new WarehouseInfo(
                warehouseNumber,
                warehouse.getOwnerId(),
                warehouse.getWarehouseMaterialAmount().amount(),
                warehouse.getMaterialType());
    }
}
