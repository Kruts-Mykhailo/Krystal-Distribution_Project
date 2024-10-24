package integration;

import be.kdg.prog6.Main;
import be.kdg.prog6.adapter.out.db.appointment.AppointmentConverter;
import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaRepository;
import be.kdg.prog6.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ContextConfiguration(classes = { Main.class })
@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(IntegrationTest.class);

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private AppointmentJpaRepository appointmentJpaRepository;

    private AppointmentJpaEntity appointment;

    @BeforeEach
    public void setup() {
        Appointment tempAppointment = new Appointment(
                new LicensePlate("X-999-111"),
                MaterialType.CEMENT,
                LocalDateTime.now(),
                new WarehouseNumber("W-01"),
                AppointmentStatus.ON_SITE);
        appointment = AppointmentConverter.toJpaEntity(tempAppointment);
        appointment = appointmentJpaRepository.save(appointment);
    }


    @Test
    @WithMockUser
    void shouldReturnAppointmentON_SITE() throws Exception{
        // Arrange
        String licensePlate = "X-999-111";

        // Act
        final ResultActions result = mockMvc.perform(get(String.format("/trucks/%s", licensePlate)).with(csrf()));

        String responseContent = result.andReturn().getResponse().getContentAsString();
        logger.info("Response Content: {}", responseContent);

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.currentStatus").value("ON_SITE"));
    }

    @AfterEach
    public void tearDown() {
        appointmentJpaRepository.delete(appointment);
    }

}
