package be.kdg.prog6.adapters.in.api;

import be.kdg.prog6.ports.in.MatchSOAndPOUseCase;
import be.kdg.prog6.ports.in.PlanBunkeringOperationUseCase;
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

    public VesselController(MatchSOAndPOUseCase matchSOAndPOUseCase, PlanBunkeringOperationUseCase planBunkeringOperationUseCase) {
        this.matchSOAndPOUseCase = matchSOAndPOUseCase;
        this.planBunkeringOperationUseCase = planBunkeringOperationUseCase;
    }

    @PostMapping("/{vesselNumber}")
    public ResponseEntity<?> matchSOAndPOForVessel(@PathVariable String vesselNumber){
        matchSOAndPOUseCase.matchSOAndPO(vesselNumber);
        return ResponseEntity.ok().body("SO and PO matched for vessel " + vesselNumber);
    }

    @PostMapping("/{vesselNumber}/bunkeringOperations/{date}")
    public ResponseEntity<?> scheduleBunkeringOperation(@PathVariable String vesselNumber, @PathVariable LocalDate date){
        planBunkeringOperationUseCase.planBO(date, vesselNumber);
        return ResponseEntity.ok().body("Bunkering operation for %s is scheduled for %s".formatted(vesselNumber, date));
    }
}
