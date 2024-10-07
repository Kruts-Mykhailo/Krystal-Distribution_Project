package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.ShippingOrder;

public interface MatchShippingOrderUseCase {
    void initiateLoading(ShippingOrder shippingOrder);
}
