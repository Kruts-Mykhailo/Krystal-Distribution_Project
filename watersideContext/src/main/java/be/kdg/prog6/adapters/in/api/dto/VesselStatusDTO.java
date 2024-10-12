package be.kdg.prog6.adapters.in.api.dto;

import java.time.LocalDate;


public record VesselStatusDTO(String veselNumber, LocalDate arrivalDate, String shipmentOrderStatus,
                              BunkeringOperationDTO bunkeringOperation,
                              InspectionOperationDTO inspectionOperation, Boolean ordersMatched) {
}
