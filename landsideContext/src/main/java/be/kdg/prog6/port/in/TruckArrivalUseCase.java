package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.Truck;

import java.time.LocalDateTime;

public interface TruckArrivalUseCase {
    void arriveToFacility(Truck truck, LocalDateTime arrivalTime);
}
