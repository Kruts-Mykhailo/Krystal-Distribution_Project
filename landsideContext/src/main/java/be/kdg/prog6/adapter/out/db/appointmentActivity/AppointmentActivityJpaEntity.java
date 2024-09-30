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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID activityId;

    private String licensePlate;

    private String activityType;

    private LocalDateTime dateTime;

    private String truckStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private AppointmentJpaEntity appointment;

    public AppointmentActivityJpaEntity() {

    }

    public AppointmentActivityJpaEntity(String licensePlate, String activityType, String truckStatus, LocalDateTime dateTime, AppointmentJpaEntity appointment) {
        this.licensePlate = licensePlate;
        this.activityType = activityType;
        this.truckStatus = truckStatus;
        this.dateTime = dateTime;
        this.appointment = appointment;
    }
}
