package stubbing;

import be.kdg.prog6.Main;
import be.kdg.prog6.core.CalculateStorageCostsUseCaseImpl;
import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ContextConfiguration(classes = { Main.class })
public class StubbingTest {

    private CalculateStorageCostsUseCaseImpl calculateStorageCostsUseCase;

    private WarehouseFoundPortStub warehouseFoundPortStub;

    @BeforeEach
    void setUp() {
        warehouseFoundPortStub = new WarehouseFoundPortStub();
        calculateStorageCostsUseCase = new CalculateStorageCostsUseCaseImpl(warehouseFoundPortStub);
    }


    @Test
    void shouldCalculateStorageCosts() {
        Seller.SellerId id = new Seller.SellerId(UUID.randomUUID());
        Double storageCosts = calculateStorageCostsUseCase.calculate(id);

        assertEquals(storageCosts, 6000.0);
        Assertions.assertEquals(warehouseFoundPortStub.getWarehousesBySellerId(id).size(), 2);
        Assertions.assertEquals(warehouseFoundPortStub.getWarehousesBySellerId(id).get(0).getMaterialType(), MaterialType.GYPSUM);
        Assertions.assertEquals(warehouseFoundPortStub.getWarehousesBySellerId(id).get(1).getMaterialType(), MaterialType.IRON_ORE);
    }


}
