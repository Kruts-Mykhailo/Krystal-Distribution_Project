package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.PONumber;

public interface PurchaseOrderFulfilledUseCase {
    void deductMaterial(PONumber poNumber);
}
