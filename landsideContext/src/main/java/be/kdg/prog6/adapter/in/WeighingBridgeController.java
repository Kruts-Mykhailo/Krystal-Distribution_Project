package be.kdg.prog6.adapter.in;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bridge")
public class WeighingBridgeController {

    @PostMapping("/{licensePlate}/{payloadWeight}")
    public ResponseEntity<?> passWeighingBridge(@PathVariable String licensePlate, @PathVariable Double payloadWeight) {


        return null;
    }
}
