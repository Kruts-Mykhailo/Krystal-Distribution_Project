package be.kdg.prog6.adapter.in;

import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.WBT;
import be.kdg.prog6.port.in.LeaveWeighingBridgeUseCase;
import be.kdg.prog6.port.in.PassBridgeCommand;
import be.kdg.prog6.port.in.EnterWeighingBridgeUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/bridge")
public class WeighingBridgeController {

    private final EnterWeighingBridgeUseCase enterWeighingBridgeUseCase;
    private final LeaveWeighingBridgeUseCase leaveWeighingBridgeUseCase;

    public WeighingBridgeController(EnterWeighingBridgeUseCase enterWeighingBridgeUseCase, LeaveWeighingBridgeUseCase leaveWeighingBridgeUseCase) {
        this.enterWeighingBridgeUseCase = enterWeighingBridgeUseCase;
        this.leaveWeighingBridgeUseCase = leaveWeighingBridgeUseCase;
    }

    @PostMapping("/{licensePlate}/{payloadWeight}/{isEnter}")
    public ResponseEntity<?> passWeighingBridge(@PathVariable String licensePlate,
                                                @PathVariable Double payloadWeight,
                                                @PathVariable Boolean isEnter) {

        PassBridgeCommand passBridgeCommand = new PassBridgeCommand(
                new LicensePlate(licensePlate),
                payloadWeight,
                LocalDateTime.now());
        if (isEnter) {
            enterWeighingBridgeUseCase.passWeighingBridge(passBridgeCommand);
        } else {
            Optional<WBT> wbtOptional = leaveWeighingBridgeUseCase.leaveWeighingBridge(passBridgeCommand);
            if (wbtOptional.isPresent()) {
                WBT wbt = wbtOptional.get();
                return ResponseEntity.ok(wbt);
            }
            return ResponseEntity.badRequest().body(String.format("Truck %s could not leave facility.", licensePlate));
        }
        return ResponseEntity.ok().build();
    }
}
