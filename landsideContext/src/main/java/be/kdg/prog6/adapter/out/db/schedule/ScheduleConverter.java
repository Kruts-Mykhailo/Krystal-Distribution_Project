package be.kdg.prog6.adapter.out.db.schedule;

import be.kdg.prog6.adapter.out.db.appointment.AppointmentConverter;
import be.kdg.prog6.domain.DaySchedule;

public class ScheduleConverter {

    public static DaySchedule fromJpaFetched(ScheduleJpaEntity scheduleJpaEntity) {
        return new DaySchedule(
                scheduleJpaEntity.getScheduleId(),
                scheduleJpaEntity.getScheduleDate(),
                scheduleJpaEntity.getAppointmentJpaEntities()
                        .stream()
                        .map(AppointmentConverter::toAppointment)
                        .toList());
    }
}
