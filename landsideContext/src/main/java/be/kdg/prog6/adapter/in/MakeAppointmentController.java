package be.kdg.prog6.adapter.in;

import be.kdg.prog6.adapter.in.dto.AppointmentDTO;
import be.kdg.prog6.adapter.in.dto.AppointmentRequestDTO;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.CreateAppointmentCommand;
import be.kdg.prog6.port.in.MakeAppointmentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/appointment")
public class MakeAppointmentController {

    private final MakeAppointmentUseCase makeAppointmentUseCase;

    public MakeAppointmentController(MakeAppointmentUseCase makeAppointmentUseCase) {
        this.makeAppointmentUseCase = makeAppointmentUseCase;
    }

    @PostMapping("/{customerId}")
    public ResponseEntity<?> makeAppointment(@RequestBody AppointmentRequestDTO appointmentRequestDTO,
                                                          @PathVariable UUID customerId) {
        CreateAppointmentCommand command = new CreateAppointmentCommand(
                MaterialType.valueOf(appointmentRequestDTO.getMaterialType()),
                new LicensePlate(appointmentRequestDTO.getLicensePlate()),
                new Seller.SellerId(customerId),
                appointmentRequestDTO.getScheduleDateTime());

        Optional<Appointment> appointment = makeAppointmentUseCase.makeAppointment(command);

        if (appointment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Appointment could not be created");
        }
        Appointment appointmentCreated = appointment.get();
        AppointmentDTO appointmentDTO = new AppointmentDTO(
                appointmentCreated.getTruckLicensePlate().licensePlate(),
                appointmentCreated.getMaterialType().name(),
                appointmentCreated.getWarehouseNumber(),
                appointmentCreated.getAppointmentDateTime());

        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentDTO);
    }
}
