package smoke;

import be.kdg.prog6.Main;
import be.kdg.prog6.adapter.out.db.warehouse.WarehouseInfoJpaEntity;
import be.kdg.prog6.adapter.out.db.warehouse.WarehouseInfoJpaRepository;
import be.kdg.prog6.domain.WarehouseInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ContextConfiguration(classes = Main.class)
public class SmokeTest {

    @Autowired
    private WarehouseInfoJpaRepository warehouseInfoJpaRepository;


    @Test
    void shouldSeedWarehouses() {
        List<WarehouseInfoJpaEntity> warehouseInfos = warehouseInfoJpaRepository.findAll();
        assertEquals(5, warehouseInfos.size());
        assertTrue(warehouseInfos.stream().allMatch(w -> w.getWarehouseNumber().startsWith("W")));
    }
}
