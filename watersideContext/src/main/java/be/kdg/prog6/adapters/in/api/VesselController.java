package be.kdg.prog6.adapters.in.api;

import be.kdg.prog6.ports.in.MatchSOAndPOUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vessels")
public class VesselController {

    private final MatchSOAndPOUseCase matchSOAndPOUseCase;

    public VesselController(MatchSOAndPOUseCase matchSOAndPOUseCase) {
        this.matchSOAndPOUseCase = matchSOAndPOUseCase;
    }

    @PostMapping("/{vesselNumber}")
    public ResponseEntity<?> matchSOAndPOForVessel(@PathVariable String vesselNumber){
        matchSOAndPOUseCase.matchSOAndPO(vesselNumber);
        return ResponseEntity.ok().body("SO and PO matched for vessel " + vesselNumber);
    }
}
