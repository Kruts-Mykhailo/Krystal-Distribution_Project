package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.TruckWeightRecord;

import java.util.List;
import java.util.UUID;

public interface TruckWeightRecordFoundPort {
    TruckWeightRecord getTruckWeightRecord(UUID appointmentId);
}
