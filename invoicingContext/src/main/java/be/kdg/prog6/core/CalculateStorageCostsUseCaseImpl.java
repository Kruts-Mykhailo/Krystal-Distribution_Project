package be.kdg.prog6.core;

import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.port.in.CalculateStorageCostsUseCase;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateStorageCostsUseCaseImpl implements CalculateStorageCostsUseCase {

    private final WarehouseFoundPort warehouseFoundPort;

    public CalculateStorageCostsUseCaseImpl(WarehouseFoundPort warehouseFoundPort) {
        this.warehouseFoundPort = warehouseFoundPort;
    }

    @Override
    @Transactional
    public Double calculate(Seller.SellerId sellerId) {
        List<Warehouse> warehouseList = warehouseFoundPort.getWarehousesBySellerId(sellerId);
        return warehouseList.stream().mapToDouble(Warehouse::calculateStorageFee).sum();
    }
}
