package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.DaySchedule;

import java.time.LocalDate;
import java.util.List;

public interface ScheduleFoundPort {
    List<DaySchedule> findAllAfterDate(LocalDate date);
}
