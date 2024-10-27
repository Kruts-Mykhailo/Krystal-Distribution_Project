package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.Appointment;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CheckAllTrucksStatusesUseCase {
    List<Appointment> checkStatusesOfTrucks(LocalDate date);
}
