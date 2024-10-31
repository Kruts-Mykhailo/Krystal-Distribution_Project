package integration;

import be.kdg.prog6.Main;
import be.kdg.prog6.adapter.out.db.appointment.AppointmentConverter;
import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaRepository;
import be.kdg.prog6.adapter.out.db.seller.SellerConverter;
import be.kdg.prog6.adapter.out.db.seller.SellerJpaEntity;
import be.kdg.prog6.adapter.out.db.seller.SellerJpaRepository;
import be.kdg.prog6.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
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

    @Autowired
    private SellerJpaRepository sellerJpaRepository;

    private AppointmentJpaEntity appointment;

    @BeforeEach
    public void setup() {
        Seller seller = new Seller(new Seller.SellerId(UUID.randomUUID()), "test");
        SellerJpaEntity sellerJpaEntity = sellerJpaRepository.save(SellerConverter.toJpa(seller));
        Appointment tempAppointment = new Appointment(
                new LicensePlate("X-999-111"),
                MaterialType.CEMENT,
                LocalDateTime.now(),
                new WarehouseNumber("W-01"),
                TruckArrivalStatus.ON_SITE,
                seller);
        appointment = AppointmentConverter.toJpaEntity(tempAppointment);
        appointment.setSeller(sellerJpaEntity);
        appointment = appointmentJpaRepository.save(appointment);
    }


    @Test
    void shouldReturnAppointmentsON_SITE() throws Exception{
        // Arrange

        // Act
        final ResultActions result = mockMvc
                .perform(get("/trucks")
                .with(jwt().authorities(new SimpleGrantedAuthority("user"))));


        String responseContent = result.andReturn().getResponse().getContentAsString();
        logger.info("Response Content: {}", responseContent);

        // Assert
        result.andExpect(status().isOk())
                .andExpect(jsonPath("$.amount").value(1));
    }

    @AfterEach
    public void tearDown() {
        appointmentJpaRepository.delete(appointment);
    }

}
