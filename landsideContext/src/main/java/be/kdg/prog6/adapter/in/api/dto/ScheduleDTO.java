package be.kdg.prog6.adapter.in.api.dto;

import be.kdg.prog6.domain.DaySchedule;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ScheduleDTO {

    private LocalDate date;
    private Integer windowAppointmentLimit;
    private List<TimeWindowDTO> timeWindows;

    public ScheduleDTO(LocalDate date, Integer windowAppointmentLimit, List<TimeWindowDTO> timeWindows) {
        this.date = date;
        this.windowAppointmentLimit = windowAppointmentLimit;
        this.timeWindows = timeWindows;
    }

    public LocalDate getDate() {
        return date;
    }

    public Integer getWindowAppointmentLimit() {
        return windowAppointmentLimit;
    }

    public List<TimeWindowDTO> getTimeWindows() {
        return timeWindows;
    }

    public static List<ScheduleDTO> fromDomain(List<DaySchedule> daySchedules) {
        return daySchedules.stream()
                .map(s -> new ScheduleDTO(
                        s.getScheduleDate(),
                        s.getTruckAmountPerWindow(),
                        s.getAppointments().stream()
                                .collect(Collectors.groupingBy(
                                        appointment -> appointment.getAppointmentDateTime().getHour(),
                                        Collectors.summingInt(appointment -> 1)))
                                .entrySet()
                                .stream()
                                .map(entry -> new TimeWindowDTO(entry.getKey(), entry.getValue()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
