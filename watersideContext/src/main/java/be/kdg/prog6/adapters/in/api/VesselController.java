package be.kdg.prog6.adapters.in.api;

import be.kdg.prog6.ports.in.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/vessels")
public class VesselController {

    private final MatchSOAndPOUseCase matchSOAndPOUseCase;
    private final PlanBunkeringOperationUseCase planBunkeringOperationUseCase;
    private final CompleteVesselInspectionUseCase completeVesselInspectionUseCase;

    public VesselController(MatchSOAndPOUseCase matchSOAndPOUseCase, PlanBunkeringOperationUseCase planBunkeringOperationUseCase, CompleteVesselInspectionUseCase completeVesselInspectionUseCase) {
        this.matchSOAndPOUseCase = matchSOAndPOUseCase;
        this.planBunkeringOperationUseCase = planBunkeringOperationUseCase;
        this.completeVesselInspectionUseCase = completeVesselInspectionUseCase;
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
}
