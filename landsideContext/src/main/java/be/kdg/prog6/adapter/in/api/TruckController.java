package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.adapter.in.api.dto.TruckArrivalDTO;
import be.kdg.prog6.adapter.in.api.dto.TrucksOnSiteDTO;
import be.kdg.prog6.port.in.CheckAllTrucksStatusesUseCase;
import be.kdg.prog6.port.in.GetTruckAmountOnSiteUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/trucks")
public class TruckController {

    private final GetTruckAmountOnSiteUseCase getTruckAmountOnSiteUseCase;
    private final CheckAllTrucksStatusesUseCase checkAllTrucksStatusesUseCase;

    public TruckController(GetTruckAmountOnSiteUseCase getTruckAmountOnSiteUseCase, CheckAllTrucksStatusesUseCase checkAllTrucksStatusesUseCase) {
        this.getTruckAmountOnSiteUseCase = getTruckAmountOnSiteUseCase;
        this.checkAllTrucksStatusesUseCase = checkAllTrucksStatusesUseCase;
    }

    @GetMapping
    public ResponseEntity<TrucksOnSiteDTO> getAllTrucksOnSiteNumber(){
        TrucksOnSiteDTO amount = new TrucksOnSiteDTO(getTruckAmountOnSiteUseCase.getTruckAmountOnSite());
        return ResponseEntity.ok().body(amount);
    }

    @GetMapping("/{date}")
    public ResponseEntity<?> getTruckArrivalStatusByDate(@PathVariable LocalDate date){
        List<TruckArrivalDTO> trucks = checkAllTrucksStatusesUseCase.checkStatusesOfTrucks(date)
                .stream()
                .map(TruckArrivalDTO::from)
                .toList();
        if (trucks.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(trucks);
    }
}
