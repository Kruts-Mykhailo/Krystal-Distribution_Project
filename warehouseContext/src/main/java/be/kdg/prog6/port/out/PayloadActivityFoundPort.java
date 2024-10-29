package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.PayloadActivity;
import be.kdg.prog6.domain.WarehouseNumber;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PayloadActivityFoundPort {
    Optional<PayloadActivity> getActivityByWarehouseAndArrivalTimeAndAmount(WarehouseNumber number, LocalDateTime arrivalDateTime, Double amount);
}
