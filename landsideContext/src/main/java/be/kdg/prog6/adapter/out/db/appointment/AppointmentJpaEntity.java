package be.kdg.prog6.adapter.out.db.appointment;

import be.kdg.prog6.adapter.out.db.schedule.ScheduleJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Setter
@Getter
@Entity(name = "appointment")
@Table(name = "appointments", catalog = "landside")
public class AppointmentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID appointmentId;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private String materialType;

    @Column(nullable = false, columnDefinition = "BINARY(16)")
    private UUID warehouseId;

    @Column(nullable = false)
    private LocalDateTime appointmentDateTime;

    @Column(name = "warehouse_number", nullable = false)
    private int warehouseNumber;

    @Column(nullable = false)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    private ScheduleJpaEntity schedule;

    public AppointmentJpaEntity(String licensePlate, String materialType, UUID warehouseId, LocalDateTime appointmentDateTime, int warehouseNumber, String status) {
        this.licensePlate = licensePlate;
        this.materialType = materialType;
        this.warehouseId = warehouseId;
        this.appointmentDateTime = appointmentDateTime;
        this.warehouseNumber = warehouseNumber;
        this.status = status;
    }

    public AppointmentJpaEntity() {

    }

}
