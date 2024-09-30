package be.kdg.prog6.adapter.in;


import be.kdg.prog6.adapter.in.dto.ArrivalDTO;
import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.TruckActivity;
import be.kdg.prog6.port.in.TruckArrivalUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.logging.Logger;

@Controller
@RequestMapping("/gate")
public class GateController {

    private final TruckArrivalUseCase truckArrivalUseCase;
    private final Logger logger = Logger.getLogger(GateController.class.getName());

    public GateController(TruckArrivalUseCase truckArrivalUseCase) {
        this.truckArrivalUseCase = truckArrivalUseCase;
    }

    @PostMapping("/arrive")
    public ResponseEntity<?> arriveToFacility(@RequestBody ArrivalDTO arrivalDTO) {
        logger.info(arrivalDTO.toString());
        Optional<TruckActivity> arrivalResult = truckArrivalUseCase.arriveToFacility(
                new LicensePlate(arrivalDTO.getLicensePlate()),
                arrivalDTO.getArrivalTime()
        );
        if (arrivalResult.isPresent()) {
            return ResponseEntity.ok(arrivalDTO);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Truck arrival failed");
    }
}
