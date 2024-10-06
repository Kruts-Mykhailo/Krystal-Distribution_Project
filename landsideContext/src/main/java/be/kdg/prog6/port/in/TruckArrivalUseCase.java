package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.*;

import java.time.LocalDateTime;

public interface TruckArrivalUseCase {
    void arriveToFacility(TruckArrivalCommand truckArrivalCommand);
}
