package be.kdg.prog6.adapters.in.api;

import be.kdg.prog6.ports.in.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/vessels")
public class VesselController {

    private final MatchSOAndPOUseCase matchSOAndPOUseCase;
    private final PlanBunkeringOperationUseCase planBunkeringOperationUseCase;
    private final CompleteVesselInspectionUseCase completeVesselInspectionUseCase;
    private final GetAllOutstandingInspectionOperationsUseCase getAllOutstandingInspectionOperationsUseCase;

    public VesselController(MatchSOAndPOUseCase matchSOAndPOUseCase, PlanBunkeringOperationUseCase planBunkeringOperationUseCase, CompleteVesselInspectionUseCase completeVesselInspectionUseCase, GetAllOutstandingInspectionOperationsUseCase getAllOutstandingInspectionOperationsUseCase) {
        this.matchSOAndPOUseCase = matchSOAndPOUseCase;
        this.planBunkeringOperationUseCase = planBunkeringOperationUseCase;
        this.completeVesselInspectionUseCase = completeVesselInspectionUseCase;
        this.getAllOutstandingInspectionOperationsUseCase = getAllOutstandingInspectionOperationsUseCase;
    }

    @PostMapping("/{vesselNumber}")
    public ResponseEntity<?> matchSOAndPOForVessel(@PathVariable String vesselNumber){
        matchSOAndPOUseCase.matchSOAndPO(vesselNumber);
        return ResponseEntity.ok().body("SO and PO matched for vessel " + vesselNumber);
    }

    @PostMapping("/{vesselNumber}/bunkeringOperations/{date}")
    public ResponseEntity<?> scheduleBunkeringOperation(@PathVariable String vesselNumber, @PathVariable LocalDate date){
        planBunkeringOperationUseCase.planBO(new PlanBunkeringOperationCommand(vesselNumber, date));
        return ResponseEntity.ok().body("Bunkering operation for %s is scheduled for %s".formatted(vesselNumber, date));
    }

    @PostMapping("/{vesselNumber}/inspectionOperations/{signature}")
    public ResponseEntity<?> completeVesselInspection(@PathVariable String vesselNumber,
                                                      @PathVariable String signature){
        VesselInspectionCommand command = new VesselInspectionCommand(vesselNumber, signature, LocalDate.now());
        completeVesselInspectionUseCase.completeVesselInspection(command);
        return ResponseEntity.ok().body("Vessel inspection for %s is completed".formatted(vesselNumber));

    }

    @GetMapping("/outstanding-inspection-operations")
    public ResponseEntity<List<OutstandingIODTO>> getVesselsWithOutstandingInspectionOperation(){
        List<OutstandingIODTO> ships = getAllOutstandingInspectionOperationsUseCase.
                getAll().
                stream().
                map(o -> new OutstandingIODTO(o.getVesselNumber())).
                toList();

        if (ships.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(ships);
    }
}
