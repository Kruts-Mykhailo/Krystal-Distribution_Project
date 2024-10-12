package be.kdg.prog6.ports.in;

import java.time.LocalDate;

public record VesselInspectionCommand(String vesselNumber, String inspectorSignature, LocalDate inspectionDate) {
}
