package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.LicensePlate;

public interface DumpPayloadUseCase {
    Appointment dumpPayload(LicensePlate licensePlate);
}
