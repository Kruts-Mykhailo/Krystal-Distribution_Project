package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.port.in.CalculateStorageCostsUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/commissions")
public class CommissionController {

    private final CalculateStorageCostsUseCase calculateStorageCostsUseCase;


    public CommissionController(CalculateStorageCostsUseCase calculateStorageCostsUseCase) {
        this.calculateStorageCostsUseCase = calculateStorageCostsUseCase;
    }

    @GetMapping("/{sellerId}")
    public ResponseEntity<?> calculateStorageCosts(@PathVariable UUID sellerId) {
        Double amount = calculateStorageCostsUseCase.calculate(new Seller.SellerId(sellerId));
        return ResponseEntity.ok(amount);
    }
}
