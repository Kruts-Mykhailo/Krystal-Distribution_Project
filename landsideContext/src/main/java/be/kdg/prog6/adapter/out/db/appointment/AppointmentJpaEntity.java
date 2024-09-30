package be.kdg.prog6.adapter.out.db.appointment;

import be.kdg.prog6.adapter.out.db.appointmentActivity.AppointmentActivityJpaEntity;
import be.kdg.prog6.adapter.out.db.schedule.ScheduleJpaEntity;
import be.kdg.prog6.adapter.out.db.truckweight.TruckWeightJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "appointments", catalog = "landside")
public class AppointmentJpaEntity {

    @Id
    @Column(nullable = false)
    private UUID appointmentId;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private String materialType;

    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID warehouseId;

    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    @Column(nullable = false)
    private LocalDateTime appointmentEndDateTime;

    @Column(name = "warehouse_number", nullable = false)
    private int warehouseNumber;

    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private ScheduleJpaEntity schedule;

    @OneToMany(mappedBy = "appointment")
    private List<AppointmentActivityJpaEntity> activities;

    @OneToOne(mappedBy = "appointment")
    private TruckWeightJpaEntity recordedTruckWeight;

    public AppointmentJpaEntity(UUID appointmentId, String licensePlate, String materialType, UUID warehouseId, LocalDateTime appointmentDateTime, int warehouseNumber, String status) {
        this.appointmentId = appointmentId;
        this.licensePlate = licensePlate;
        this.materialType = materialType;
        this.warehouseId = warehouseId;
        this.appointmentDateTime = appointmentDateTime;
        this.appointmentEndDateTime = appointmentDateTime.plusHours(1);
        this.warehouseNumber = warehouseNumber;
        this.status = status;
    }

    public AppointmentJpaEntity(UUID appointmentId) {
        this.appointmentId = appointmentId;
    }

    public AppointmentJpaEntity() {

    }

}
