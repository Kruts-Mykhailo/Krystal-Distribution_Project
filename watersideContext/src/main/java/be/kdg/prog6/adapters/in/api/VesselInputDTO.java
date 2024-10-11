package be.kdg.prog6.adapters.in.api;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.UOM;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class VesselInputDTO {
    private String purchaseOrderNumber;
    private String customerEnterpriseNumber;
    private List<OrderLineDTO> orderLines;
    private LocalDate departureDate;

    public record OrderLineDTO(Double weight, String materialType, String uom) {

    }
}
