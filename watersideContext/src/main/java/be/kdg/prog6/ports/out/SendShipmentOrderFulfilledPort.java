package be.kdg.prog6.ports.out;

import be.kdg.prog6.domain.PONumber;

public interface SendShipmentOrderFulfilledPort {
    void deductMaterialFromWarehouse(PONumber poNumber);
}
