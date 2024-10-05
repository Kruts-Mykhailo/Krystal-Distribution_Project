package be.kdg.prog6.adapter.out.db.appointmentActivity;


import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import be.kdg.prog6.domain.Appointment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "appointment_activities", catalog = "landside")
public class AppointmentActivityJpaEntity {

    @Id
    private UUID activityId;

    private String licensePlate;

    private String activityType;

    private LocalDateTime dateTime;

    private String truckStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppointmentJpaEntity appointment;

    public AppointmentActivityJpaEntity() {

    }

    public AppointmentActivityJpaEntity(UUID activityId, String licensePlate, String activityType, LocalDateTime dateTime, String truckStatus, AppointmentJpaEntity appointment) {
        this.activityId = activityId;
        this.licensePlate = licensePlate;
        this.activityType = activityType;
        this.dateTime = dateTime;
        this.truckStatus = truckStatus;
        this.appointment = appointment;
    }
}
