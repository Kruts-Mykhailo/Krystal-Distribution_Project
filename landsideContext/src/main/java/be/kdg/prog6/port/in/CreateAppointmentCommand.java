package be.kdg.prog6.port.in;


import be.kdg.prog6.domain.Truck;

public record CreateAppointmentCommand(Truck truck) {
    public CreateAppointmentCommand {
    }
}
