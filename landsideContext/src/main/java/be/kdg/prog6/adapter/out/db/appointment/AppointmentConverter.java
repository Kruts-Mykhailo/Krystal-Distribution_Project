package be.kdg.prog6.adapter.out.db.appointment;

import be.kdg.prog6.domain.*;

public class AppointmentConverter {

    public static Appointment toAppointment(AppointmentJpaEntity a) {
        return new Appointment(
                a.getAppointmentId(),
                new LicensePlate(a.getLicensePlate()),
                MaterialType.valueOf(a.getMaterialType()),
                a.getAppointmentDateTime(),
                a.getWarehouseId(),
                a.getWarehouseNumber(),
                AppointmentStatus.valueOf(a.getStatus()),
                a.getActivities().stream().map(ac -> new AppointmentActivity(
                        new LicensePlate(ac.getLicensePlate()),
                        ActivityType.valueOf(ac.getActivityType()),
                        ac.getDateTime(),
                        AppointmentStatus.valueOf(ac.getTruckStatus())
                )).toList()
        );
    }

    public static AppointmentJpaEntity toJpaEntity(Appointment appointment) {
        return new AppointmentJpaEntity(
                appointment.getId(),
                appointment.getTruckLicensePlate().licensePlate(),
                appointment.getMaterialType().name(),
                appointment.getWarehouseId(),
                appointment.getAppointmentDateTime(),
                appointment.getWarehouseNumber(),
                appointment.getAppointmentStatus().name()
        );
    }
}
