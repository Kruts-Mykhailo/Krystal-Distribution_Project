package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.DaySchedule;

import java.util.List;

public interface GetAllSchedulesUseCase {
    List<DaySchedule> getAllSchedules();
}
