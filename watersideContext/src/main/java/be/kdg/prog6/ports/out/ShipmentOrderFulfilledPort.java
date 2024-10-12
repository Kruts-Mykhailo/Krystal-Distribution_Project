package be.kdg.prog6.ports.out;

import be.kdg.prog6.domain.ShipmentOrder;

public interface ShipmentOrderFulfilledPort {
    void deductMaterialFromWarehouse(String poNumber);
}
