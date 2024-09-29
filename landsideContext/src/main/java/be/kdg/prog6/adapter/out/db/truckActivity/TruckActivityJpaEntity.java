package be.kdg.prog6.adapter.out.db.truckActivity;


import be.kdg.prog6.domain.LicensePlate;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "truck_activities", catalog = "landside")
public class TruckActivityJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID activityId;

    private String licensePlate;

    private String activityType;

    private LocalDateTime dateTime;

    private String truckStatus;

    public TruckActivityJpaEntity() {

    }

    public TruckActivityJpaEntity(String licensePlate, String activityType, String truckStatus, LocalDateTime dateTime) {
        this.licensePlate = licensePlate;
        this.activityType = activityType;
        this.truckStatus = truckStatus;
        this.dateTime = dateTime;
    }
}
