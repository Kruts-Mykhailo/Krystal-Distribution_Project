package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.*;

import java.time.LocalDateTime;
import java.util.Optional;

public interface TruckArrivalUseCase {
    Optional<AppointmentActivity> arriveToFacility(LicensePlate licensePlate, LocalDateTime arrivalTime);
}
