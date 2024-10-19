package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.ActivityType;

import java.util.UUID;

public interface ProjectWarehouseInfoPort {
    void projectWarehouseCapacity(UUID warehouseId, Double capacity, ActivityType activityType);
}
