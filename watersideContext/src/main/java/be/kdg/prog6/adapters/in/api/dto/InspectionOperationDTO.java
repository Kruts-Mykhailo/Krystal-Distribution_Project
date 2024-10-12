package be.kdg.prog6.adapters.in.api.dto;

import java.time.LocalDate;

public record InspectionOperationDTO(String status, LocalDate date, String signature) {
}
