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

    private final int TRUCK_AMOUNT_DEFAULT = 1;


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

    // Find the first hour for which the amount of trucks is scheduled less than truckAmountPerWindow
    private Optional<LocalDateTime> getTimeWindow(){
        Map<LocalDateTime, Long> appointmentCountByHour = appointments.stream()
                .collect(Collectors.groupingBy(
                        appointment -> appointment.getAppointmentDateTime().withMinute(0).withSecond(0).withNano(0),
                        Collectors.counting()
                ));

        for (int hour = 0; hour < 24; hour++) {
            LocalDateTime currentHour = scheduleDate.atTime(hour, 0);
            long trucksScheduled = appointmentCountByHour.getOrDefault(currentHour, 0L);

            if (trucksScheduled < truckAmountPerWindow) {
                return Optional.of(currentHour);  // Return the first available time window
            }
        }

        return Optional.empty();
    }

    public Optional<Appointment> scheduleAppointment(LicensePlate truckLicensePlate, MaterialType materialType, UUID warehouseId, int warehouseNumber) {
        Optional<LocalDateTime> timeWindow = getTimeWindow();
        return timeWindow.map(localDateTime -> new Appointment(truckLicensePlate, materialType, localDateTime,warehouseId, warehouseNumber));

    }

}
