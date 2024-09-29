package be.kdg.prog6.adapter.out.db.schedule;

import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "schedules", catalog = "landside")
public class ScheduleJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID scheduleId;

    @Column(nullable = false)
    private LocalDate scheduleDate;

    @OneToMany(mappedBy = "schedule")
    private List<AppointmentJpaEntity> appointmentJpaEntities;

    public ScheduleJpaEntity(UUID id) {
        this.scheduleId = id;
    }

    public ScheduleJpaEntity() {

    }

    public ScheduleJpaEntity(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

}
