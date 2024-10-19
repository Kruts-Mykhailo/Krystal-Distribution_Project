package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.PONumber;

public interface MatchPOAndSOUseCase {
    void matchOrders(PONumber shippingOrder);
}
