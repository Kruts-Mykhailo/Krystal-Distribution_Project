package be.kdg.prog6.port.in;

import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.PDT;

public interface DumpPayloadUseCase {
    PDT dumpPayload(LicensePlate licensePlate);
}
