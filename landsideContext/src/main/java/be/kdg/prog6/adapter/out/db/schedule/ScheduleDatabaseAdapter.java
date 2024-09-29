package be.kdg.prog6.adapter.out.db.schedule;

import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaRepository;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.out.ScheduleDetailsPort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class ScheduleDatabaseAdapter implements ScheduleDetailsPort {

    private final ScheduleJpaRepository scheduleJpaRepository;

    public ScheduleDatabaseAdapter(ScheduleJpaRepository scheduleJpaRepository) {
        this.scheduleJpaRepository = scheduleJpaRepository;
    }

    @Override
    public DaySchedule loadScheduleByDate(LocalDate date) {
        ScheduleJpaEntity schedule = scheduleJpaRepository.findByScheduleDate(date)
                .orElseThrow(() -> new IllegalArgumentException("No schedule found for the given date: " + date));

        List<Appointment> appointments = schedule.getAppointmentJpaEntities()
                .stream()
                .map(this::toAppointment).toList();

        return new DaySchedule(schedule.getScheduleId(), schedule.getScheduleDate(), appointments);
    }

    @Override
    public DaySchedule createOrLoadScheduleByDate(LocalDate date) {
        Optional<ScheduleJpaEntity> scheduleJpaEntity = scheduleJpaRepository.findByScheduleDate(date);
        ScheduleJpaEntity schedule;
        if (scheduleJpaEntity.isEmpty()) {
            schedule = scheduleJpaRepository.save(new ScheduleJpaEntity(date));
            return new DaySchedule(schedule.getScheduleId(), schedule.getScheduleDate());
        }
        schedule = scheduleJpaEntity.get();
        List<Appointment> appointments = schedule.getAppointmentJpaEntities()
                .stream()
                .map(this::toAppointment).toList();
        return new DaySchedule(schedule.getScheduleId(), schedule.getScheduleDate(), appointments);
    }

    private Appointment toAppointment(AppointmentJpaEntity a) {
        return new Appointment(
                a.getAppointmentId(),
                new LicensePlate(a.getLicensePlate()),
                MaterialType.valueOf(a.getMaterialType()),
                a.getAppointmentDateTime(),
                a.getWarehouseId(),
                a.getWarehouseNumber(),
                AppointmentStatus.valueOf(a.getStatus())
        );
    }
}
