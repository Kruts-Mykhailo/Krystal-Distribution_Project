package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.domain.WarehouseInfo;
import be.kdg.prog6.port.in.GetWarehouseInfoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final GetWarehouseInfoUseCase getWarehouseInfoUseCase;

    public WarehouseController(GetWarehouseInfoUseCase getWarehouseInfoUseCase) {
        this.getWarehouseInfoUseCase = getWarehouseInfoUseCase;
    }

    @GetMapping("/{warehouseNumber}")
    public ResponseEntity<?> getWarehouseInfo(@PathVariable int warehouseNumber) {
        WarehouseInfo warehouseInfo = getWarehouseInfoUseCase.getWarehouseInfo(warehouseNumber);
        return ResponseEntity.ok().body(warehouseInfo);
    }
}
