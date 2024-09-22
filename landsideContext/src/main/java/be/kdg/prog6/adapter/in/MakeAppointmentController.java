package be.kdg.prog6.adapter.in;

import be.kdg.prog6.adapter.in.dto.AppointmentDTO;
import be.kdg.prog6.adapter.in.dto.TruckDTO;
import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.Truck;
import be.kdg.prog6.port.in.CreateAppointmentCommand;
import be.kdg.prog6.port.in.MakeAppointmentUseCase;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class MakeAppointmentController {

    private final MakeAppointmentUseCase makeAppointmentUseCase;
    private final ModelMapper mapper;

    public MakeAppointmentController(MakeAppointmentUseCase makeAppointmentUseCase, ModelMapper mapper) {
        this.makeAppointmentUseCase = makeAppointmentUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<AppointmentDTO> makeAppointment(@RequestBody TruckDTO truckDTO) {
        Truck truck = mapper.map(truckDTO, Truck.class);
        CreateAppointmentCommand command = new CreateAppointmentCommand(truck);
        Appointment appointment = makeAppointmentUseCase.makeAppointment(command);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.map(appointment, AppointmentDTO.class));
    }
}
