package be.kdg.prog6.adapter.in;



import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.AppointmentActivity;
import be.kdg.prog6.port.in.TruckArrivalUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Logger;

@Controller
@RequestMapping("/gate")
public class GateController {

    private final TruckArrivalUseCase truckArrivalUseCase;
    private final Logger logger = Logger.getLogger(GateController.class.getName());

    public GateController(TruckArrivalUseCase truckArrivalUseCase) {
        this.truckArrivalUseCase = truckArrivalUseCase;
    }

    @PostMapping("/{licensePlate}/arrive")
    public ResponseEntity<?> arriveToFacility(@PathVariable String licensePlate) {
        logger.info(licensePlate + " truck arrived to facility.");

        LocalDateTime arrivalDateTime = LocalDateTime.now();

        Optional<AppointmentActivity> arrivalResult = truckArrivalUseCase.arriveToFacility(
                new LicensePlate(licensePlate),
                arrivalDateTime
        );

        if (arrivalResult.isPresent()) {

            Random random = new Random();
            int weighingBridgeNumber = random.nextInt(10000);

            return ResponseEntity.ok(
                    String.format("Truck %s arrived to facility at %s. Weighing bridge number: %d",
                            licensePlate, arrivalDateTime, weighingBridgeNumber)
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Truck arrival failed");
    }
}
