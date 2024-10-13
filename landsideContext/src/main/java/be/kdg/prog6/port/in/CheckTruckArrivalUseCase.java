package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.LicensePlate;

public interface CheckTruckArrivalUseCase {
    Appointment checkTruckArrival(LicensePlate licensePlate);
}
