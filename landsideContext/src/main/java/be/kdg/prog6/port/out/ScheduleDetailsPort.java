package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.DaySchedule;

import java.time.LocalDate;

public interface ScheduleDetailsPort {
    DaySchedule loadScheduleByDate(LocalDate date);
    DaySchedule createOrLoadScheduleByDate(LocalDate date);

}
