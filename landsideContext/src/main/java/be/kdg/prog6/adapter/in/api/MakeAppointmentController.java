package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.adapter.in.dto.AppointmentDTO;
import be.kdg.prog6.adapter.in.dto.AppointmentRequestDTO;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.CreateAppointmentCommand;
import be.kdg.prog6.port.in.MakeAppointmentUseCase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        Appointment appointment = makeAppointmentUseCase.makeAppointment(command);

        AppointmentDTO appointmentDTO = new AppointmentDTO(
                appointment.getTruckLicensePlate().licensePlate(),
                appointment.getMaterialType().name(),
                appointment.getWarehouseNumber(),
                appointment.getAppointmentDateTime());

        return ResponseEntity.status(HttpStatus.CREATED).body(appointmentDTO);
    }
}
