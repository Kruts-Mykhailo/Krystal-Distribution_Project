package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.PayloadActivity;
import be.kdg.prog6.domain.WarehouseNumber;

public interface PayloadActivityUpdatedPort {
    void updateWeight(PayloadActivity payloadActivity, WarehouseNumber warehouseNumber, Double netWeight);
}
