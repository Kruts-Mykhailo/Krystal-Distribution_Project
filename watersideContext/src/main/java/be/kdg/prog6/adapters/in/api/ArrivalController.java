package be.kdg.prog6.adapters.in.api;

import be.kdg.prog6.domain.OrderLine;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.InputSOAndVesselInfoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/arrivals")
public class ArrivalController {

    private final InputSOAndVesselInfoUseCase inputSOAndVesselInfoUseCase;

    public ArrivalController(InputSOAndVesselInfoUseCase inputSOAndVesselInfoUseCase) {
        this.inputSOAndVesselInfoUseCase = inputSOAndVesselInfoUseCase;
    }


    @PostMapping("/{vesselNumber}")
    public ResponseEntity<?> inputInfoOnArrival(@PathVariable String vesselNumber,
                                                @RequestBody VesselInputDTO vesselInputDTO) {
        ShipmentOrder shipmentOrder = new ShipmentOrder(
                vesselInputDTO.getPurchaseOrderNumber(),
                vesselInputDTO.getOrderLines().stream().map(ol -> new OrderLine(
                        ol.materialType(),
                        ol.weight(),
                        ol.uom()
                )).toList(),
                vesselInputDTO.getCustomerEnterpriseNumber(),
                vesselNumber,
                LocalDate.now(),
                vesselInputDTO.getDepartureDate()
        );
        inputSOAndVesselInfoUseCase.inputInformation(shipmentOrder);
        return ResponseEntity.ok().body("Ship %s arrived successfully".formatted(vesselNumber));
    }
}
