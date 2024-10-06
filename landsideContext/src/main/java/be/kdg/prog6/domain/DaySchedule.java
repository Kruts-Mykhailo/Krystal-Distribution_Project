package be.kdg.prog6.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DaySchedule {

    private final UUID id;

    private final LocalDate scheduleDate;

    private final List<Appointment> appointments;

    private final int truckAmountPerWindow;

    private final int TRUCK_AMOUNT_DEFAULT = 40;


    public DaySchedule(UUID id, LocalDate scheduleDate, List<Appointment> appointments) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.appointments = appointments;
        this.truckAmountPerWindow = TRUCK_AMOUNT_DEFAULT;
    }

    public DaySchedule(UUID id, LocalDate scheduleDate) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.appointments = new ArrayList<>();
        this.truckAmountPerWindow = TRUCK_AMOUNT_DEFAULT;
    }

    public UUID getId() {
        return id;
    }

    private boolean isTimeWindowAvailable(LocalDateTime desiredScheduleDateTime) {
        Map<LocalDateTime, Long> appointmentCountByHour = appointments.stream()
                .collect(Collectors.groupingBy(
                        appointment -> appointment.getAppointmentDateTime().withMinute(0).withSecond(0).withNano(0),
                        Collectors.counting()
                ));
        long trucksScheduled = appointmentCountByHour.getOrDefault(desiredScheduleDateTime, 0L);
        return trucksScheduled < truckAmountPerWindow;
    }

    public Optional<Appointment> scheduleAppointment(LocalDateTime scheduleDateTime,
                                                     LicensePlate truckLicensePlate,
                                                     MaterialType materialType,
                                                     UUID warehouseId,
                                                     int warehouseNumber) {
        if (isTimeWindowAvailable(scheduleDateTime)) {
            return Optional.of(new Appointment(truckLicensePlate, materialType, scheduleDateTime, warehouseId, warehouseNumber, AppointmentStatus.SCHEDULED));
        }
        return Optional.empty();

    }

}
