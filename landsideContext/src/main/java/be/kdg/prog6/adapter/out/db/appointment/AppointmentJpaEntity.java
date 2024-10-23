package be.kdg.prog6.adapter.out.db.appointment;

import be.kdg.prog6.adapter.out.db.appointmentActivity.AppointmentActivityJpaEntity;
import be.kdg.prog6.adapter.out.db.schedule.ScheduleJpaEntity;
import be.kdg.prog6.adapter.out.db.truckweight.TruckWeightJpaEntity;
import be.kdg.prog6.domain.WarehouseNumber;
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

    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    @Column(nullable = false)
    private LocalDateTime appointmentEndDateTime;

    @Column(name = "warehouse_number", nullable = false)
    private String warehouseNumber;

    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private ScheduleJpaEntity schedule;

    @OneToMany(mappedBy = "appointment")
    private List<AppointmentActivityJpaEntity> activities;

    @OneToMany(mappedBy = "appointment")
    private List<TruckWeightJpaEntity> recordedTruckWeight;

    public AppointmentJpaEntity(UUID appointmentId, String licensePlate, String materialType, LocalDateTime appointmentDateTime, String warehouseNumber, String status) {
        this.appointmentId = appointmentId;
        this.licensePlate = licensePlate;
        this.materialType = materialType;
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
