package be.kdg.prog6.core;

import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.domain.WarehouseInfo;
import be.kdg.prog6.port.in.GetInfosOfWarehousesUseCase;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetInfosOfWarehousesUseCaseImpl implements GetInfosOfWarehousesUseCase {

    private final WarehouseFoundPort warehouseFoundPort;

    public GetInfosOfWarehousesUseCaseImpl(WarehouseFoundPort warehouseFoundPort) {
        this.warehouseFoundPort = warehouseFoundPort;
    }

    @Override
    @Transactional
    public List<WarehouseInfo> getInfosOfWarehouses() {
        return warehouseFoundPort.getAllWarehouses()
                .stream()
                .map(Warehouse::provideInformation)
                .toList();
    }
}
