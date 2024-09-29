package be.kdg.prog6.adapter.out.db.appointment;

import be.kdg.prog6.adapter.out.db.schedule.ScheduleJpaEntity;
import be.kdg.prog6.domain.Appointment;
import be.kdg.prog6.domain.AppointmentStatus;
import be.kdg.prog6.domain.LicensePlate;
import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.port.out.AppointmentCreatedPort;
import be.kdg.prog6.port.out.AppointmentUpdatedPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Component
public class AppointmentCreatedAdapter implements AppointmentCreatedPort, AppointmentUpdatedPort {

    private final AppointmentJpaRepository appointmentJpaRepository;

    public AppointmentCreatedAdapter(AppointmentJpaRepository appointmentJpaRepository) {
        this.appointmentJpaRepository = appointmentJpaRepository;
    }

    @Override
    public void saveAppointment(Appointment appointment, UUID scheduleId) {
        AppointmentJpaEntity appointmentJpaEntity = new AppointmentJpaEntity(
                appointment.getId(),
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

    @Override
    public void updateAppointmentStatus(AppointmentStatus status, UUID appointmentId) {
        var appointmentJpaEntity = appointmentJpaRepository.findById(appointmentId);
        if (appointmentJpaEntity.isPresent()) {
            var appointment = appointmentJpaEntity.get();
            appointment.setStatus(status.name());
            appointmentJpaRepository.save(appointment);
        }
    }

    @Override
    public Optional<Appointment> getAppointmentByArrivalTime(LicensePlate licensePlate, LocalDateTime arrivalTime) {
        Optional<AppointmentJpaEntity> appointmentJpaEntity = appointmentJpaRepository
                .findEarliestScheduledAppointmentWithArrivalDateTime(licensePlate.licensePlate(), arrivalTime);
        return appointmentJpaEntity.map(this::toAppointment);
    }

    private Appointment toAppointment(AppointmentJpaEntity a) {
        return new Appointment(
                a.getAppointmentId(),
                new LicensePlate(a.getLicensePlate()),
                MaterialType.valueOf(a.getMaterialType()),
                a.getAppointmentDateTime(),
                a.getWarehouseId(),
                a.getWarehouseNumber(),
                AppointmentStatus.valueOf(a.getStatus())
        );
    }
}
