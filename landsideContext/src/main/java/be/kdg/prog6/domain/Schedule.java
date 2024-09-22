package be.kdg.prog6.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {

    private List<Window> availableWindows;

    private LocalDate scheduleDate;

    public static Window getFreeWindow(){
        return new Window(LocalDateTime.now(), LocalDateTime.now().plusHours(1));
    }
}
