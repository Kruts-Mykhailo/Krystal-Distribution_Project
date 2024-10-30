package be.kdg.prog6.adapter.out.db.truckweight;


import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(catalog = "landside", name = "truck_weight_records")
public class TruckWeightJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String licensePlate;

    private Double weightRecorded;

    private LocalDateTime recordTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppointmentJpaEntity appointment;

    public TruckWeightJpaEntity(String licensePlate, Double weightRecorded, LocalDateTime recordTime) {
        this.licensePlate = licensePlate;
        this.weightRecorded = weightRecorded;
        this.recordTime = recordTime;
    }

    public TruckWeightJpaEntity() {
    }
}
