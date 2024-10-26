package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.domain.WarehouseInfo;
import be.kdg.prog6.domain.WarehouseNumber;
import be.kdg.prog6.port.in.GetInfosOfWarehousesUseCase;
import be.kdg.prog6.port.in.GetWarehouseInfoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/warehouses")
public class WarehouseController {

    private final GetWarehouseInfoUseCase getWarehouseInfoUseCase;
    private final GetInfosOfWarehousesUseCase getInfosOfWarehousesUseCase;

    public WarehouseController(GetWarehouseInfoUseCase getWarehouseInfoUseCase, GetInfosOfWarehousesUseCase getInfosOfWarehousesUseCase) {
        this.getWarehouseInfoUseCase = getWarehouseInfoUseCase;
        this.getInfosOfWarehousesUseCase = getInfosOfWarehousesUseCase;
    }

    @GetMapping("/{warehouseNumber}")
    public ResponseEntity<?> getWarehouseInfo(@PathVariable String warehouseNumber) {
        WarehouseInfo warehouseInfo = getWarehouseInfoUseCase.getWarehouseInfo(new WarehouseNumber(warehouseNumber));
        return ResponseEntity.ok().body(warehouseInfo);
    }

    @GetMapping
    public ResponseEntity<?> getWarehouses() {
        List<WarehouseInfo> warehouseInfoList = getInfosOfWarehousesUseCase.getInfosOfWarehouses();
        if (warehouseInfoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(warehouseInfoList);
    }
}
