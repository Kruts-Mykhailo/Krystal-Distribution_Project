package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.PayloadActivity;
import be.kdg.prog6.domain.WarehouseNumber;

import java.util.List;

public interface PayloadActivitySavedPort {
    void savePayloadActivity(PayloadActivity payloadActivity, WarehouseNumber warehouseNumber);
}
