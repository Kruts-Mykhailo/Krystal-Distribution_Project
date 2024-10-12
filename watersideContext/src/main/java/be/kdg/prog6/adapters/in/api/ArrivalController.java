package be.kdg.prog6.adapters.in.api;

import be.kdg.prog6.adapters.in.api.dto.VesselStatusDTO;
import be.kdg.prog6.adapters.in.api.dto.VesselStatusDTOConverter;
import be.kdg.prog6.adapters.in.api.dto.VesselInputDTO;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.ports.in.InputSOAndVesselInfoUseCase;
import be.kdg.prog6.ports.in.InputVesselInfoCommand;
import be.kdg.prog6.ports.in.ViewShipmentArrivalsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/arrivals")
public class ArrivalController {

    private final InputSOAndVesselInfoUseCase inputSOAndVesselInfoUseCase;
    private final ViewShipmentArrivalsUseCase viewShipmentArrivalsUseCase;

    public ArrivalController(InputSOAndVesselInfoUseCase inputSOAndVesselInfoUseCase, ViewShipmentArrivalsUseCase viewShipmentArrivalsUseCase) {
        this.inputSOAndVesselInfoUseCase = inputSOAndVesselInfoUseCase;
        this.viewShipmentArrivalsUseCase = viewShipmentArrivalsUseCase;
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


    @GetMapping
    public ResponseEntity<?> viewAllShipmentsArrivals(){
        List<VesselStatusDTO> shipmentsArrivals = viewShipmentArrivalsUseCase.getAllShipmentArrivals().
                stream().
                map(VesselStatusDTOConverter::convert).
                toList();
        if (shipmentsArrivals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(shipmentsArrivals);
    }


}
