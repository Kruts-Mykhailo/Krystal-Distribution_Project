package be.kdg.prog6.adapter.in;


import be.kdg.prog6.adapter.in.dto.ArrivalDTO;
import be.kdg.prog6.domain.AppointmentStatus;
import be.kdg.prog6.domain.TruckActivity;
import be.kdg.prog6.port.in.TruckArrivalUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/truck")
public class TruckActivityController {

    private final TruckArrivalUseCase truckArrivalUseCase;

    public TruckActivityController(TruckArrivalUseCase truckArrivalUseCase) {
        this.truckArrivalUseCase = truckArrivalUseCase;
    }

    @PostMapping("/arrive")
    public ResponseEntity<?> arriveToFacility(@RequestBody ArrivalDTO arrivalDTO) {
        Optional<TruckActivity> arrivalResult = truckArrivalUseCase.arriveToFacility(
                arrivalDTO.getLicensePlate(),
                arrivalDTO.getArrivalTime()
        );
        if (arrivalResult.isPresent()) {
            return ResponseEntity.ok(arrivalResult.get());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Truck arrival failed");
    }
}
