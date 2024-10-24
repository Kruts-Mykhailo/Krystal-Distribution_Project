package core;

import be.kdg.prog6.Main;
import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import be.kdg.prog6.core.MakeAppointmentUseCaseImpl;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.CreateAppointmentCommand;
import be.kdg.prog6.port.out.AppointmentCreatedPort;
import be.kdg.prog6.port.out.AppointmentFoundPort;
import be.kdg.prog6.port.out.ScheduleUpdatedPort;
import be.kdg.prog6.port.out.WarehouseProjectionFoundPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = { Main.class })
public class TestMakeAppointmentUsingMockitoTest {

    WarehouseProjectionFoundPort warehouseProjectionFoundPort;

    MakeAppointmentUseCaseImpl makeAppointmentUseCase;

    @Autowired
    AppointmentFoundPort appointmentFoundPort;

    @Autowired
    ScheduleUpdatedPort scheduleUpdatedPort;

    @Autowired
    AppointmentCreatedPort appointmentCreatedPort;

    @BeforeEach
    public void setUp() {
        warehouseProjectionFoundPort = mock(WarehouseProjectionFoundPort.class);
        makeAppointmentUseCase = new MakeAppointmentUseCaseImpl(
                appointmentCreatedPort,
                scheduleUpdatedPort,
                warehouseProjectionFoundPort
        );
    }

    @Test
    void shouldMakeAppointment() {
        // Arrange
        Seller.SellerId sellerId = new Seller.SellerId(UUID.randomUUID());
        LocalDateTime dateTime = LocalDateTime.now().withMinute(0).withSecond(0).withNano(0);
        LicensePlate licensePlate = new LicensePlate("XXX-999-111");
        MaterialType materialType = MaterialType.GYPSUM;

        CreateAppointmentCommand command = new CreateAppointmentCommand(
                materialType,
                licensePlate,
                sellerId,
                dateTime);

        WarehouseInfo warehouseInfo = mock(WarehouseInfo.class);
        when(warehouseProjectionFoundPort.getWarehouse(command.sellerId(), command.materialType()))
                .thenReturn(warehouseInfo);
        when(warehouseInfo.isFullCapacity())
                .thenReturn(false);
        when(warehouseInfo.warehouseNumber())
                .thenReturn(new WarehouseNumber("W-00"));

        // Act
        Appointment appointment = makeAppointmentUseCase.makeAppointment(command);
        Appointment foundAppointment = appointmentFoundPort.getAppointmentOfTruck(licensePlate);


        // Assert
        assertEquals(appointment.getAppointmentDateTime(), dateTime);
        assertEquals(appointment.getMaterialType(), MaterialType.GYPSUM);
        assertEquals(foundAppointment.getWarehouseNumber(), new WarehouseNumber("W-00"));
        verify(warehouseProjectionFoundPort, times(1));

    }

}
