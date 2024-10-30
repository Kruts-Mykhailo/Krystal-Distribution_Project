package be.kdg.prog6.adapter.out.db.appointmentActivity;

import be.kdg.prog6.domain.AppointmentActivity;

public class AppointmentActivityConverter {
    public static AppointmentActivityJpaEntity toJpa(AppointmentActivity ac) {
        return  new AppointmentActivityJpaEntity(
                ac.activityId(),
                ac.licensePlate().licensePlate(),
                ac.activityType().name(),
                ac.localDateTime(),
                ac.status().name());
    }
}
