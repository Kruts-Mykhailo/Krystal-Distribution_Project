package be.kdg.prog6.adapters.in.api.dto;

import lombok.Getter;

import java.time.LocalDate;


public record VesselArrivalDTO(String veselNumber, LocalDate arrivalDate, String shipmentOrderStatus,
                               BunkeringOperationDTO bunkeringOperation,
                               InspectionOperationDTO inspectionOperation, Boolean ordersMatched) {
}
