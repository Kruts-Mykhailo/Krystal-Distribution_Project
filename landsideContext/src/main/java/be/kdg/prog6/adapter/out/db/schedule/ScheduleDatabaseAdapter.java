package be.kdg.prog6.adapter.out.db.schedule;

import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaRepository;
import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.DaySchedule;
import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.MaterialType;
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
        Optional<ScheduleJpaEntity> scheduleJpaEntity = scheduleJpaRepository.findByScheduleDate(date);
        ScheduleJpaEntity schedule;
        if (scheduleJpaEntity.isEmpty()) {
            schedule = scheduleJpaRepository.save(new ScheduleJpaEntity(date));
            return new DaySchedule(schedule.getScheduleId(), schedule.getScheduleDate());
        }
        schedule = scheduleJpaEntity.get();
        List<Appointment> appointments = schedule.getAppointmentJpaEntities()
                .stream()
                .map(a -> new Appointment(
                        new LicensePlate(a.getLicensePlate()),
                        MaterialType.valueOf(a.getMaterialType()),
                        a.getAppointmentDateTime(),
                        a.getWarehouseId(),
                        a.getWarehouseNumber()
                )).toList();
        return new DaySchedule(schedule.getScheduleId(), schedule.getScheduleDate(), appointments);
    }
}
