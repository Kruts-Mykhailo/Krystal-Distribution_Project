package be.kdg.prog6.domain;

import be.kdg.prog6.adapter.exceptions.AppointmentCannotBeScheduledException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class DaySchedule {

    private final UUID id;

    private final LocalDate scheduleDate;

    private final List<Appointment> appointments;

    private final int truckAmountPerWindow;

    private final int TRUCK_AMOUNT_DEFAULT = 3;


    public DaySchedule(UUID id, LocalDate scheduleDate, List<Appointment> appointments) {
        this.id = id;
        this.scheduleDate = scheduleDate;
        this.appointments = appointments;
        this.truckAmountPerWindow = TRUCK_AMOUNT_DEFAULT;
    }


    public UUID getId() {
        return id;
    }

    private boolean isTimeWindowAvailable(LocalDateTime desiredScheduleDateTime) {
        Map<LocalDateTime, Long> appointmentCountByHour = appointments.stream()
                .collect(Collectors.groupingBy(
                        appointment -> appointment.getScheduledArrivalTime().withMinute(0).withSecond(0).withNano(0),
                        Collectors.counting()
                ));
        long trucksScheduled = appointmentCountByHour.getOrDefault(desiredScheduleDateTime, 0L);
        return trucksScheduled < truckAmountPerWindow;
    }

    public Appointment scheduleAppointment(LocalDateTime scheduleDateTime,
                                                     LicensePlate truckLicensePlate,
                                                     MaterialType materialType,
                                                     WarehouseNumber warehouseNumber,
                                                     Seller seller) {
        if (!isTimeWindowAvailable(scheduleDateTime)) {
            throw new AppointmentCannotBeScheduledException(
                    String.format("Appointment cannot be scheduled for %s", scheduleDateTime));
        }
        return new Appointment(truckLicensePlate,
                materialType,
                scheduleDateTime,
                warehouseNumber,
                TruckArrivalStatus.SCHEDULED,
                seller);
    }

    public int getTruckAmountPerWindow() {
        return truckAmountPerWindow;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public LocalDate getScheduleDate() {
        return scheduleDate;
    }
}
