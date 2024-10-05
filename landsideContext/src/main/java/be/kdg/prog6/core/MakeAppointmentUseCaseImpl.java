package be.kdg.prog6.core;

import be.kdg.prog6.adapter.in.exceptions.AppointmentCannotBeScheduledException;
import be.kdg.prog6.adapter.in.exceptions.WarehouseHasFullCapacityException;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.CreateAppointmentCommand;
import be.kdg.prog6.port.in.MakeAppointmentUseCase;
import be.kdg.prog6.port.out.AppointmentCreatedPort;
import be.kdg.prog6.port.out.ScheduleDetailsPort;
import be.kdg.prog6.port.out.WarehouseInfoPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Appointment makeAppointment(CreateAppointmentCommand createAppointmentCommand) {
        WarehouseInfo warehouseInfo = warehouseInfoPort.getWarehouse(
                createAppointmentCommand.sellerId(),
                createAppointmentCommand.materialType()
        );

        DaySchedule schedule = scheduleDetailsPort.createOrLoadScheduleByDate(createAppointmentCommand
                .scheduleDateTime()
                .toLocalDate());

        Optional<Appointment> appointment = schedule.scheduleAppointment(
                createAppointmentCommand.scheduleDateTime(),
                createAppointmentCommand.licensePlate(),
                createAppointmentCommand.materialType(),
                warehouseInfo.warehouseId(),
                warehouseInfo.warehouseNumber()
        );
        if (appointment.isEmpty()) {
            throw new AppointmentCannotBeScheduledException(String.format("Appointment cannot be scheduled for %s", createAppointmentCommand.scheduleDateTime()));
        }
        if (warehouseInfo.fullCapacity()) {
            throw new WarehouseHasFullCapacityException(String.format("Warehouse %d has full capacity", warehouseInfo.warehouseNumber()));
        }
        Appointment newAppointment = appointment.get();
        appointmentCreatedPort.saveAppointment(newAppointment, schedule.getId());
        return newAppointment;
    }
}
