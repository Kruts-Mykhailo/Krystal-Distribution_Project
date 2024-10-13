package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.adapter.in.api.dto.TruckArrivalDTO;
import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.port.in.CheckTruckArrivalUseCase;
import be.kdg.prog6.port.in.GetTruckAmountOnSiteUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    private final CheckTruckArrivalUseCase checkTruckArrivalUseCase;
    private final GetTruckAmountOnSiteUseCase getTruckAmountOnSiteUseCase;

    public TruckController(CheckTruckArrivalUseCase checkTruckArrivalUseCase, GetTruckAmountOnSiteUseCase getTruckAmountOnSiteUseCase) {
        this.checkTruckArrivalUseCase = checkTruckArrivalUseCase;
        this.getTruckAmountOnSiteUseCase = getTruckAmountOnSiteUseCase;
    }

    @GetMapping("/{licensePlate}")
    public ResponseEntity<TruckArrivalDTO> getTruckStatus(@PathVariable String licensePlate) {
        Appointment appointment = checkTruckArrivalUseCase.checkTruckArrival(new LicensePlate(licensePlate));
        return ResponseEntity.ok().body(TruckArrivalDTO.from(appointment));
    }


    @GetMapping
    public ResponseEntity<?> getAllTrucksOnSiteNumber(){
        int amount = getTruckAmountOnSiteUseCase.getTruckAmountOnSite();
        if (amount == 0) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(amount);
    }
}
