package be.kdg.prog6.adapter.out.db.appointment;

import be.kdg.prog6.adapter.exceptions.AppointmentNotFoundException;
import be.kdg.prog6.adapter.out.db.appointmentActivity.AppointmentActivityConverter;
import be.kdg.prog6.adapter.out.db.appointmentActivity.AppointmentActivityJpaEntity;
import be.kdg.prog6.adapter.out.db.appointmentActivity.AppointmentActivityJpaRepository;
import be.kdg.prog6.adapter.out.db.schedule.ScheduleJpaEntity;
import be.kdg.prog6.adapter.out.db.seller.SellerJPAEntity;
import be.kdg.prog6.domain.*;
import be.kdg.prog6.port.out.AppointmentCreatedPort;
import be.kdg.prog6.port.out.AppointmentFoundPort;
import be.kdg.prog6.port.out.AppointmentUpdatedPort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AppointmentAdapter implements AppointmentCreatedPort, AppointmentUpdatedPort, AppointmentFoundPort {

    private final AppointmentJpaRepository appointmentJpaRepository;
    private final AppointmentActivityJpaRepository appointmentActivityJpaRepository;

    public AppointmentAdapter(AppointmentJpaRepository appointmentJpaRepository, AppointmentActivityJpaRepository appointmentActivityJpaRepository) {
        this.appointmentJpaRepository = appointmentJpaRepository;
        this.appointmentActivityJpaRepository = appointmentActivityJpaRepository;
    }

    @Override
    public void saveAppointment(Appointment appointment, UUID scheduleId) {
        AppointmentJpaEntity appointmentJpaEntity = AppointmentConverter.toJpaEntity(appointment);
        appointmentJpaEntity.setSchedule(new ScheduleJpaEntity(scheduleId));
        appointmentJpaEntity.setSeller(new SellerJPAEntity(appointment.getSeller().getSellerId().id()));
        appointmentJpaRepository.save(appointmentJpaEntity);
    }

    @Override
    public void updateStatus(Appointment appointment) {
        AppointmentJpaEntity foundAppointment = appointmentJpaRepository.findById(appointment.getId())
                .orElseThrow(() -> new AppointmentNotFoundException(
                        "Appointment %s not found".formatted(appointment.getId())));

        foundAppointment.setStatus(appointment.getTruckArrivalStatus().name());

        AppointmentJpaEntity savedAppointment = appointmentJpaRepository.save(foundAppointment);

        List<AppointmentActivityJpaEntity> activities = appointment.getAppointmentActivities()
                .stream()
                .map(ac -> AppointmentActivityConverter.toJpaEntity(ac, savedAppointment))
                .collect(Collectors.toList());
        appointmentActivityJpaRepository.saveAll(activities);

    }


    @Override
    public void updateAllStatuses(List<Appointment> appointments) {
        List<AppointmentJpaEntity> existingAppointments = appointmentJpaRepository.findAllById(
                appointments.stream().map(Appointment::getId).collect(Collectors.toList())
        );

        for (AppointmentJpaEntity appointmentJpaEntity : existingAppointments) {
            Appointment appointmentToUpdate = appointments.stream()
                    .filter(a -> a.getId().equals(appointmentJpaEntity.getAppointmentId()))
                    .findFirst()
                    .orElseThrow(() -> new AppointmentNotFoundException(
                            "Appointment %s not found".formatted(appointmentJpaEntity.getAppointmentId())));

            if (appointmentToUpdate != null) {
                appointmentJpaEntity.setStatus(appointmentToUpdate.getTruckArrivalStatus().name());
            }
        }

    }

    @Override
    public Appointment getAppointmentByArrivalTime(LicensePlate licensePlate, LocalDateTime arrivalTime) {
        return appointmentJpaRepository
                .findEarliestScheduledAppointmentWithArrivalDateTime(licensePlate.licensePlate(), arrivalTime)
                .map(AppointmentConverter::toAppointment)
                .orElseThrow(() -> new AppointmentNotFoundException(
                        String.format("Appointment for %s has not been found", licensePlate.licensePlate())));

    }

    @Override
    public Appointment getByLicensePlateAndNotStatus(LicensePlate licensePlate, TruckArrivalStatus status) {
        return appointmentJpaRepository
                .findByLicensePlateAndNotStatusFetched(licensePlate.licensePlate(), status.name())
                .map(AppointmentConverter::toAppointment)
                .orElseThrow(() -> new AppointmentNotFoundException(
                        "Appointment for %s could not be found".formatted(licensePlate.licensePlate())));
    }

    @Override
    public List<Appointment> getAllTruckAppointmentsByDate(LocalDate when) {
        return appointmentJpaRepository
                .findAllByAppointmentDateTimeBetweenFetched(when.atStartOfDay(), when.plusDays(1).atStartOfDay())
                .stream()
                .map(AppointmentConverter::toAppointment)
                .collect(Collectors.toList());
    }


    @Override
    public List<Appointment> getAllAppointmentsByStatusIn(List<TruckArrivalStatus> status) {
        return appointmentJpaRepository
                .findAllByStatusIn(status.stream().map(TruckArrivalStatus::name).toList())
                .stream()
                .map(AppointmentConverter::toAppointment)
                .collect(Collectors.toList());
    }
}
