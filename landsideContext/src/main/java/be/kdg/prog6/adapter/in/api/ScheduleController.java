package be.kdg.prog6.adapter.in.api;


import be.kdg.prog6.adapter.in.api.dto.ScheduleDTO;
import be.kdg.prog6.domain.DaySchedule;
import be.kdg.prog6.port.in.GetAllSchedulesUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final GetAllSchedulesUseCase getAllSchedulesUseCase;

    public ScheduleController(GetAllSchedulesUseCase getAllSchedulesUseCase) {
        this.getAllSchedulesUseCase = getAllSchedulesUseCase;
    }


    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAllSchedules() {
        List<DaySchedule> schedules = getAllSchedulesUseCase.getAllSchedules();
        List<ScheduleDTO> scheduleDTOs = ScheduleDTO.fromDomain(schedules);
        return ResponseEntity.ok().body(scheduleDTOs);
    }
}
