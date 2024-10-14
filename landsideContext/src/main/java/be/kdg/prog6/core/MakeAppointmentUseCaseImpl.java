package be.kdg.prog6.core;

import be.kdg.prog6.adapter.exceptions.AppointmentCannotBeScheduledException;
import be.kdg.prog6.adapter.exceptions.WarehouseHasFullCapacityException;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.CreateAppointmentCommand;
import be.kdg.prog6.port.in.MakeAppointmentUseCase;
import be.kdg.prog6.port.out.AppointmentCreatedPort;
import be.kdg.prog6.port.out.ScheduleDetailsPort;
import be.kdg.prog6.port.out.WarehouseInfoPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MakeAppointmentUseCaseImpl implements MakeAppointmentUseCase {

    private final AppointmentCreatedPort appointmentCreatedPort;
    private final ScheduleDetailsPort scheduleDetailsPort;
    private final WarehouseInfoPort warehouseInfoPort;
    private final Logger logger = Logger.getLogger(MakeAppointmentUseCaseImpl.class.getName());

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

        if (warehouseInfo.isFullCapacity()) {
            logger.warning("Fail schedule appointment. Warehouse is full");
            throw new WarehouseHasFullCapacityException(String.format("Warehouse %d has full capacity", warehouseInfo.warehouseNumber()));
        }

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
            logger.warning("Fail schedule appointment. Day full");
            throw new AppointmentCannotBeScheduledException(String.format("Appointment cannot be scheduled for %s", createAppointmentCommand.scheduleDateTime()));
        }

        Appointment newAppointment = appointment.get();
        appointmentCreatedPort.saveAppointment(newAppointment, schedule.getId());
        logger.info(String.format(
                "%s made an appointment for %s",
                createAppointmentCommand.sellerId(),
                createAppointmentCommand.scheduleDateTime()
        ));
        return newAppointment;
    }
}
