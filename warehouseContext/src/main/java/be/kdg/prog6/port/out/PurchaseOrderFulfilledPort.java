package be.kdg.prog6.port.out;

import be.kdg.prog6.events.POFulfilledEvent;

public interface PurchaseOrderFulfilledPort {
    void sendInfoForCommission(POFulfilledEvent POFulfilledEvent);
}
