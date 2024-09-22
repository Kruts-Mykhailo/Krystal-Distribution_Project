package be.kdg.prog6.core;

import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.Schedule;
import be.kdg.prog6.domain.Truck;
import be.kdg.prog6.domain.Window;
import be.kdg.prog6.port.in.CreateAppointmentCommand;
import be.kdg.prog6.port.in.MakeAppointmentUseCase;
import be.kdg.prog6.port.out.AppointmentCreatedPort;
import org.springframework.stereotype.Service;

@Service
public class MakeAppointmentUseCaseImpl implements MakeAppointmentUseCase {

    private final AppointmentCreatedPort appointmentCreatedPort;

    public MakeAppointmentUseCaseImpl(AppointmentCreatedPort appointmentCreatedPort) {
        this.appointmentCreatedPort = appointmentCreatedPort;
    }


    @Override
    public Appointment makeAppointment(CreateAppointmentCommand createAppointmentCommand) {
        Window availableWindow = Schedule.getFreeWindow();
        Truck truck = createAppointmentCommand.truck();
        Appointment appointment = new Appointment(truck.getLicensePlate(), truck.getMaterialType(), availableWindow);
        appointmentCreatedPort.saveAppointment(appointment);
        return appointment;
    }
}
