package be.kdg.prog6.adapters.in.api;

import be.kdg.prog6.domain.*;
import be.kdg.prog6.ports.in.InputSOAndVesselInfoUseCase;
import be.kdg.prog6.ports.in.InputVesselInfoCommand;
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
        InputVesselInfoCommand inputVesselInfoCommand = new InputVesselInfoCommand(
                vesselInputDTO.getPurchaseOrderNumber(),
                vesselNumber,
                vesselInputDTO.getOrderLines().stream().map(ol -> new OrderLine(
                        MaterialType.fromCode(ol.materialType()),
                        ol.weight(),
                        UOM.fromCode(ol.uom())
                )).toList(),
                vesselInputDTO.getCustomerEnterpriseNumber()
        );
        inputSOAndVesselInfoUseCase.inputInformation(inputVesselInfoCommand);
        return ResponseEntity.ok().body("Ship %s arrived successfully".formatted(vesselNumber));
    }
}
