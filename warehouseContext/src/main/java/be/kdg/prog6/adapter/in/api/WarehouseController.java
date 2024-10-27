package be.kdg.prog6.adapter.in.api;

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
    public ResponseEntity<WarehouseInfoDTO> getWarehouseInfo(@PathVariable String warehouseNumber) {
        WarehouseInfoDTO warehouseInfo = WarehouseInfoDTO.from(getWarehouseInfoUseCase.getWarehouseInfo(new WarehouseNumber(warehouseNumber)));
        return ResponseEntity.ok().body(warehouseInfo);
    }

    @GetMapping
    public ResponseEntity<List<WarehouseInfoDTO>> getWarehouses() {
        List<WarehouseInfoDTO> warehouseInfoList = getInfosOfWarehousesUseCase
                .getInfosOfWarehouses()
                .stream()
                .map(WarehouseInfoDTO::from)
                .toList();

        if (warehouseInfoList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(warehouseInfoList);
    }
}
