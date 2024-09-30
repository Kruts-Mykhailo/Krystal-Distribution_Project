package be.kdg.prog6.port.out;
import be.kdg.prog6.domain.TruckWeightRecord;

import java.util.UUID;

public interface TruckWeightSavedPort {
    void saveTruckWeight(TruckWeightRecord truckWeightRecord, UUID appointmentId);
}
