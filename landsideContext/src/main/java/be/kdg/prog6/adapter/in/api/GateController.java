package be.kdg.prog6.adapter.in.api;



import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.port.in.TruckArrivalCommand;
import be.kdg.prog6.port.in.TruckArrivalUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
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

        TruckArrivalCommand truckArrivalCommand = new TruckArrivalCommand(
                new LicensePlate(licensePlate),
                LocalDateTime.now()
        );
        truckArrivalUseCase.arriveToFacility(truckArrivalCommand);

        Random random = new Random();
        int weighingBridgeNumber = random.nextInt(10000);

        return ResponseEntity.ok(
                String.format("Truck %s arrived to facility at %s. Weighing bridge number: %d",
                        licensePlate, LocalDateTime.now(), weighingBridgeNumber)
        );

    }
}
