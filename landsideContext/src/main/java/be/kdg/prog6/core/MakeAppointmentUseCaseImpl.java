package be.kdg.prog6.core;

import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.CreateAppointmentCommand;
import be.kdg.prog6.port.in.MakeAppointmentUseCase;
import be.kdg.prog6.port.out.AppointmentCreatedPort;
import be.kdg.prog6.port.out.ScheduleDetailsPort;
import be.kdg.prog6.port.out.WarehouseInfoPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MakeAppointmentUseCaseImpl implements MakeAppointmentUseCase {

    private final AppointmentCreatedPort appointmentCreatedPort;
    private final ScheduleDetailsPort scheduleDetailsPort;
    private final WarehouseInfoPort warehouseInfoPort;

    public MakeAppointmentUseCaseImpl(AppointmentCreatedPort appointmentCreatedPort, ScheduleDetailsPort scheduleDetailsPort, WarehouseInfoPort warehouseInfoPort) {
        this.appointmentCreatedPort = appointmentCreatedPort;
        this.scheduleDetailsPort = scheduleDetailsPort;
        this.warehouseInfoPort = warehouseInfoPort;
    }


    @Override
    public Optional<Appointment> makeAppointment(CreateAppointmentCommand createAppointmentCommand) {

        WarehouseInfo warehouseInfo = warehouseInfoPort.getWarehouse(
                createAppointmentCommand.sellerId(),
                createAppointmentCommand.materialType()
        );
        DaySchedule schedule = scheduleDetailsPort.loadScheduleByDate(createAppointmentCommand.scheduleDateTime().toLocalDate());
        Optional<Appointment> appointment = schedule.scheduleAppointment(
                createAppointmentCommand.scheduleDateTime(),
                createAppointmentCommand.licensePlate(),
                createAppointmentCommand.materialType(),
                warehouseInfo.warehouseId(),
                warehouseInfo.warehouseNumber()
        );
        if (warehouseInfo.fullCapacity() || appointment.isEmpty()) {
            return Optional.empty();
        }
        Appointment newAppointment = appointment.get();
        appointmentCreatedPort.saveAppointment(newAppointment, schedule.getId());
        return Optional.of(newAppointment);
    }
}
