package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.adapter.in.api.dto.PdtDTO;
import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.PDT;
import be.kdg.prog6.port.in.DumpPayloadUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Random;

@RestController
@RequestMapping("/conveyorBelt")
public class ConveyorController {

    private final DumpPayloadUseCase dumpPayloadUseCase;

    public ConveyorController(DumpPayloadUseCase dumpPayloadUseCase) {
        this.dumpPayloadUseCase = dumpPayloadUseCase;
    }

    @PostMapping("/{licensePlate}")
    public ResponseEntity<PdtDTO> truckDumpPayload(@PathVariable String licensePlate) {
        PDT pdt = dumpPayloadUseCase.dumpPayload(new LicensePlate(licensePlate));

        Random random = new Random();
        int dockNumber = random.nextInt(10000);

        return ResponseEntity.ok(PdtDTO.fromPDT(pdt, dockNumber));

    }
}
