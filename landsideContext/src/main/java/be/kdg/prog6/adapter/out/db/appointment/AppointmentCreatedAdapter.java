package be.kdg.prog6.adapter.out.db.appointment;

import be.kdg.prog6.adapter.out.db.schedule.ScheduleJpaEntity;
import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.port.out.AppointmentCreatedPort;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class AppointmentCreatedAdapter implements AppointmentCreatedPort {

    private final AppointmentJpaRepository appointmentJpaRepository;

    public AppointmentCreatedAdapter(AppointmentJpaRepository appointmentJpaRepository) {
        this.appointmentJpaRepository = appointmentJpaRepository;
    }

    @Override
    public void saveAppointment(Appointment appointment, UUID scheduleId) {
        AppointmentJpaEntity appointmentJpaEntity = new AppointmentJpaEntity(
                appointment.getTruckLicensePlate().licensePlate(),
                appointment.getMaterialType().name(),
                appointment.getWarehouseId(),
                appointment.getAppointmentDateTime(),
                appointment.getWarehouseNumber(),
                appointment.getAppointmentStatus().name()
        );
        appointmentJpaEntity.setSchedule(new ScheduleJpaEntity(scheduleId));
        appointmentJpaRepository.save(appointmentJpaEntity);
    }
}
