package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.ShippingOrder;

public interface InitiateLoadingUseCase {
    void initiateLoading(ShippingOrder shippingOrder);
}
