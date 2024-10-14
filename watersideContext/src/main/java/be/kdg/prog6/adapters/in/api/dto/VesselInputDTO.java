package be.kdg.prog6.adapters.in.api.dto;

import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class VesselInputDTO {
    private String purchaseOrderNumber;
    private String customerEnterpriseNumber;
    private LocalDate departureDate;

}
