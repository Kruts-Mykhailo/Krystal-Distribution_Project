package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.ActivityType;
import be.kdg.prog6.domain.WarehouseNumber;

import java.util.UUID;

public interface ProjectWarehouseInfoPort {
    void projectWarehouseCapacity(WarehouseNumber number, Double capacity, ActivityType activityType);
}
