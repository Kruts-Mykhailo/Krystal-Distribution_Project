package be.kdg.prog6.adapter.out.db.appointment;

import be.kdg.prog6.domain.*;

import java.util.stream.Collectors;

public class AppointmentConverter {

    public static Appointment toAppointment(AppointmentJpaEntity a) {
        return new Appointment(
                a.getAppointmentId(),
                new LicensePlate(a.getLicensePlate()),
                MaterialType.valueOf(a.getMaterialType()),
                a.getAppointmentDateTime(),
                new WarehouseNumber(a.getWarehouseNumber()),
                TruckArrivalStatus.valueOf(a.getStatus()),
                a.getActivities().stream().map(ac -> new AppointmentActivity(
                        ac.getActivityId(),
                        new LicensePlate(ac.getLicensePlate()),
                        ActivityType.valueOf(ac.getActivityType()),
                        ac.getDateTime(),
                        TruckArrivalStatus.valueOf(ac.getTruckStatus())
                )).collect(Collectors.toList())
        );
    }

    public static AppointmentJpaEntity toJpaEntity(Appointment appointment) {
        return new AppointmentJpaEntity(
                appointment.getId(),
                appointment.getTruckLicensePlate().licensePlate(),
                appointment.getMaterialType().name(),
                appointment.getScheduledArrivalTime(),
                appointment.getWarehouseNumber().number(),
                appointment.getTruckArrivalStatus().name()
        );
    }
}
