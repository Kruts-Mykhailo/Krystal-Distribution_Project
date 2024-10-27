package testContainers;

import be.kdg.prog6.core.GetTruckAmountOnSiteUseCaseImpl;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.GetTruckAmountOnSiteUseCase;
import be.kdg.prog6.port.out.AppointmentFoundPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@AutoConfigureMockMvc
public class GetTruckAmountOnSiteTest extends AbstractDatabaseTest{


    AppointmentFoundPort appointmentFoundPort;

    GetTruckAmountOnSiteUseCase getTruckAmountOnSiteUseCase;


    @BeforeEach
    void setUp() throws Exception {
        appointmentFoundPort = mock(AppointmentFoundPort.class);
        getTruckAmountOnSiteUseCase = new GetTruckAmountOnSiteUseCaseImpl(appointmentFoundPort);
    }

    @Test
    void shouldReturnTruckAmountOnSite() throws Exception {

        // Arrange
        LocalDateTime dateTime = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);

        when(appointmentFoundPort.getAllAppointmentsByStatusIn(
                List.of(TruckArrivalStatus.ON_SITE, TruckArrivalStatus.ARRIVED_LATE)
        )).thenReturn(List.of(
                new Appointment(new LicensePlate("XXX-001"),
                        MaterialType.GYPSUM,
                        dateTime,
                        new WarehouseNumber("W-01"),
                        TruckArrivalStatus.ARRIVED_LATE),
                new Appointment(new LicensePlate("XXX-002"),
                        MaterialType.PETROULEUM_COKE,
                        dateTime,
                        new WarehouseNumber("W-01"),
                        TruckArrivalStatus.ON_SITE)
        ));

        // Act
        Integer amount = getTruckAmountOnSiteUseCase.getTruckAmountOnSite();

        // Assert
        assertEquals(2, amount);
    }
}
