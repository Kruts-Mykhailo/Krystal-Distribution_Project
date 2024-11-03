package mock;


import be.kdg.prog6.Main;
import be.kdg.prog6.core.RecordWarehouseStateUseCaseImpl;
import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.domain.WarehouseNumber;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import be.kdg.prog6.port.out.WarehouseUpdatedPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { Main.class })

public class TestSnapshottingUsingMockitoTest {

    @Mock
    private WarehouseUpdatedPort warehouseUpdatedPort;

    @Mock
    private WarehouseFoundPort warehouseFoundPort;

    @Mock
    private Warehouse warehouse;

    @InjectMocks
    private RecordWarehouseStateUseCaseImpl recordWarehouseStateUseCase;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        recordWarehouseStateUseCase = new RecordWarehouseStateUseCaseImpl(warehouseUpdatedPort, warehouseFoundPort);
    }

    @Test
    public void shouldRecordSnapshot() {
        // Arrange
        WarehouseNumber warehouseNumber = new WarehouseNumber("W-01");
        when(warehouseFoundPort.getWarehouseByNumberAfterSnapshot(warehouseNumber)).thenReturn(warehouse);

        // Act
        recordWarehouseStateUseCase.snapshot(warehouseNumber);

        // Assert
        verify(warehouseFoundPort, times(1)).getWarehouseByNumberAfterSnapshot(warehouseNumber);
        verify(warehouse, times(1)).snapshot();
        verify(warehouseUpdatedPort, times(1)).updateSnapshot(warehouse);
    }
}
