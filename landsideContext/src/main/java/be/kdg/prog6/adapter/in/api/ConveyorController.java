package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.adapter.in.api.dto.PdtCopyDTO;
import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.port.in.DumpPayloadUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("/conveyorBelt")
public class ConveyorController {

    private final DumpPayloadUseCase dumpPayloadUseCase;

    public ConveyorController(DumpPayloadUseCase dumpPayloadUseCase) {
        this.dumpPayloadUseCase = dumpPayloadUseCase;
    }

    @PostMapping("/{licensePlate}")
    public ResponseEntity<PdtCopyDTO> truckDumpPayload(@PathVariable String licensePlate) {
        Appointment updatedAppointment = dumpPayloadUseCase.dumpPayload(new LicensePlate(licensePlate));

        Random random = new Random();
        int dockNumber = random.nextInt(10000);

        return ResponseEntity.ok(new PdtCopyDTO(
                updatedAppointment.getWarehouseNumber().number(),
                LocalDateTime.now(),
                updatedAppointment.getMaterialType().name(),
                dockNumber));

    }
}
