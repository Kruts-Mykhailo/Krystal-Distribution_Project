package be.kdg.prog6.core;

import be.kdg.prog6.domain.DaySchedule;
import be.kdg.prog6.port.in.GetAllSchedulesUseCase;
import be.kdg.prog6.port.out.ScheduleFoundPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;


@Service
public class GetAllSchedulesUseCaseImpl implements GetAllSchedulesUseCase {

    private final ScheduleFoundPort scheduleFoundPort;

    public GetAllSchedulesUseCaseImpl(ScheduleFoundPort scheduleFoundPort) {
        this.scheduleFoundPort = scheduleFoundPort;
    }

    @Override
    @Transactional
    public List<DaySchedule> getAllSchedules() {
        return scheduleFoundPort.findAllAfterDate(LocalDate.now());
    }
}
