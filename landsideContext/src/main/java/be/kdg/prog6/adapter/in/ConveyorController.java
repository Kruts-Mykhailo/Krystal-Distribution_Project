package be.kdg.prog6.adapter.in;

import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.port.in.DumpPayloadUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/conveyorBelt")
public class ConveyorController {

    private final DumpPayloadUseCase dumpPayloadUseCase;

    public ConveyorController(DumpPayloadUseCase dumpPayloadUseCase) {
        this.dumpPayloadUseCase = dumpPayloadUseCase;
    }

    @PostMapping("/{licensePlate}")
    public ResponseEntity<?> truckDumpPayload(@PathVariable String licensePlate) {
        dumpPayloadUseCase.dumpPayload(new LicensePlate(licensePlate));
        return ResponseEntity.ok(
                String.format("Truck %s dumped payload at %s",
                        licensePlate, LocalDateTime.now())
        );
    }
}
