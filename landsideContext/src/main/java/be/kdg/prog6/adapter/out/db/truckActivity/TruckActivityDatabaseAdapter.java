package be.kdg.prog6.adapter.out.db.truckActivity;

import be.kdg.prog6.domain.TruckActivity;
import be.kdg.prog6.port.out.TruckActivitySavedPort;
import org.springframework.stereotype.Component;

@Component
public class TruckActivityDatabaseAdapter implements TruckActivitySavedPort {
    private final TruckActivityJpaRepository truckActivityJpaRepository;

    public TruckActivityDatabaseAdapter(TruckActivityJpaRepository truckActivityJpaRepository) {
        this.truckActivityJpaRepository = truckActivityJpaRepository;
    }

    @Override
    public void saveTruckActivity(TruckActivity truckActivity) {
        truckActivityJpaRepository.save(new TruckActivityJpaEntity(
                truckActivity.licensePlate().licensePlate(),
                truckActivity.activityType().name(),
                truckActivity.status().name(),
                truckActivity.localDateTime()));
    }
}
