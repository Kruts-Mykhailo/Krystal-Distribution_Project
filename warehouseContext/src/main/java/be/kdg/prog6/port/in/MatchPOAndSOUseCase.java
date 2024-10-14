package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.ShippingOrder;

public interface MatchPOAndSOUseCase {
    void matchOrders(ShippingOrder shippingOrder);
}
