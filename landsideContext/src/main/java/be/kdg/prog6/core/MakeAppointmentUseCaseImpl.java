package be.kdg.prog6.core;

import be.kdg.prog6.adapter.exceptions.AppointmentCannotBeScheduledException;
import be.kdg.prog6.adapter.exceptions.WarehouseHasFullCapacityException;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.in.CreateAppointmentCommand;
import be.kdg.prog6.port.in.MakeAppointmentUseCase;
import be.kdg.prog6.port.out.AppointmentCreatedPort;
import be.kdg.prog6.port.out.ScheduleUpdatedPort;
import be.kdg.prog6.port.out.WarehouseProjectionFoundPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class MakeAppointmentUseCaseImpl implements MakeAppointmentUseCase {

    private final AppointmentCreatedPort appointmentCreatedPort;
    private final ScheduleUpdatedPort scheduleUpdatedPort;
    private final WarehouseProjectionFoundPort warehouseProjectionFoundPort;
    private final Logger logger = Logger.getLogger(MakeAppointmentUseCaseImpl.class.getName());

    public MakeAppointmentUseCaseImpl(AppointmentCreatedPort appointmentCreatedPort, ScheduleUpdatedPort scheduleUpdatedPort, WarehouseProjectionFoundPort warehouseProjectionFoundPort) {
        this.appointmentCreatedPort = appointmentCreatedPort;
        this.scheduleUpdatedPort = scheduleUpdatedPort;
        this.warehouseProjectionFoundPort = warehouseProjectionFoundPort;
    }

    @Override
    @Transactional
    public Appointment makeAppointment(CreateAppointmentCommand createAppointmentCommand) {
        WarehouseInfo warehouseInfo = warehouseProjectionFoundPort.getWarehouse(
                createAppointmentCommand.sellerId(),
                createAppointmentCommand.materialType()
        );

        if (warehouseInfo.isFullCapacity()) {
            throw new WarehouseHasFullCapacityException(
                    String.format("Warehouse %s has full capacity", warehouseInfo.warehouseNumber().number()));
        }

        DaySchedule schedule = scheduleUpdatedPort.createOrLoadScheduleByDate(createAppointmentCommand
                .scheduleDateTime()
                .toLocalDate());

        Appointment appointment = schedule.scheduleAppointment(
                createAppointmentCommand.scheduleDateTime(),
                createAppointmentCommand.licensePlate(),
                createAppointmentCommand.materialType(),
                warehouseInfo.warehouseNumber(),
                warehouseInfo.getSeller()
        );

        appointmentCreatedPort.saveAppointment(appointment, schedule.getId());
        logger.info(String.format(
                "%s made an appointment for %s",
                createAppointmentCommand.sellerId(),
                createAppointmentCommand.scheduleDateTime()
        ));
        return appointment;
    }
}
