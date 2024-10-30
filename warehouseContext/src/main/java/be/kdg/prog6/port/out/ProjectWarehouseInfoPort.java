package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.ActivityType;
import be.kdg.prog6.domain.WarehouseNumber;
import be.kdg.prog6.events.WarehouseCapacityChangeEvent;

import java.util.UUID;

public interface ProjectWarehouseInfoPort {
    void projectWarehouseCapacity(WarehouseCapacityChangeEvent event);
}
