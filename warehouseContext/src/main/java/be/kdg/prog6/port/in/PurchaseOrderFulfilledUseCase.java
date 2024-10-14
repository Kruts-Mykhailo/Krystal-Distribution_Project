package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.ShippingOrder;

public interface PurchaseOrderFulfilledUseCase {
    void deductMaterial(ShippingOrder shippingOrder);
}
