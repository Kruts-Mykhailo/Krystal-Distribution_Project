package be.kdg.prog6.adapter.out.db.appointment;

import be.kdg.prog6.adapter.out.db.appointmentActivity.AppointmentActivityJpaEntity;
import be.kdg.prog6.adapter.out.db.appointmentActivity.AppointmentActivityJpaRepository;
import be.kdg.prog6.adapter.out.db.schedule.ScheduleJpaEntity;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.out.AppointmentCreatedPort;
import be.kdg.prog6.port.out.AppointmentFoundPort;
import be.kdg.prog6.port.out.AppointmentUpdatedPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class AppointmentCreatedAdapter implements AppointmentCreatedPort, AppointmentUpdatedPort, AppointmentFoundPort {

    private final AppointmentJpaRepository appointmentJpaRepository;
    private final AppointmentActivityJpaRepository appointmentActivityJpaRepository;

    private final Logger logger = Logger.getLogger(AppointmentCreatedAdapter.class.getName());

    public AppointmentCreatedAdapter(AppointmentJpaRepository appointmentJpaRepository, AppointmentActivityJpaRepository appointmentActivityJpaRepository) {
        this.appointmentJpaRepository = appointmentJpaRepository;
        this.appointmentActivityJpaRepository = appointmentActivityJpaRepository;
    }

    @Override
    public void saveAppointment(Appointment appointment, UUID scheduleId) {
        AppointmentJpaEntity appointmentJpaEntity = AppointmentConverter.toJpaEntity(appointment);
        appointmentJpaEntity.setSchedule(new ScheduleJpaEntity(scheduleId));
        appointmentJpaRepository.save(appointmentJpaEntity);
    }

    @Override
    public void updateAppointment(Appointment appointment, AppointmentStatus status) {
        var appointmentJpaEntity = appointmentJpaRepository.findById(appointment.getId());
        if (appointmentJpaEntity.isPresent()) {
            AppointmentJpaEntity foundAppointment = appointmentJpaEntity.get();
            foundAppointment.setStatus(status.name());
            AppointmentJpaEntity savedAppointment = appointmentJpaRepository.save(foundAppointment);

            List<AppointmentActivityJpaEntity> activities = appointment.getAppointmentActivities()
                    .stream()
                    .map(ac -> new AppointmentActivityJpaEntity(
                            ac.licensePlate().licensePlate(),
                            ac.activityType().name(),
                            ac.status().name(),
                            ac.localDateTime(),
                            savedAppointment
                    )).toList();
            appointmentActivityJpaRepository.saveAll(activities);
        }
    }

    @Override
    public Optional<Appointment> getAppointmentByArrivalTime(LicensePlate licensePlate, LocalDateTime arrivalTime) {
        Optional<AppointmentJpaEntity> appointmentJpaEntity = appointmentJpaRepository
                .findEarliestScheduledAppointmentWithArrivalDateTime(licensePlate.licensePlate(), arrivalTime);
        logger.info(String.format("License plate: %s appointmentTime: %s ", licensePlate.licensePlate(), arrivalTime));
        return appointmentJpaEntity.map(AppointmentConverter::toAppointment);
    }

    @Override
    public Optional<Appointment> getTruckAppointmentOnSite(LicensePlate licensePlate) {
        return appointmentJpaRepository
                .findByLicensePlateAndStatus(licensePlate.licensePlate(), AppointmentStatus.ON_SITE.name())
                .map(AppointmentConverter::toAppointment);
    }
}
