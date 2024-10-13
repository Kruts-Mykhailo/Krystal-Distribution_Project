package be.kdg.prog6.adapter.out.db.appointmentActivity;

import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import be.kdg.prog6.domain.AppointmentActivity;

public class AppointmentActivityConverter {
    public static AppointmentActivityJpaEntity toJpaEntity(AppointmentActivity ac, AppointmentJpaEntity appointmentJpaEntity) {
        return  new AppointmentActivityJpaEntity(
                ac.activityId(),
                ac.licensePlate().licensePlate(),
                ac.activityType().name(),
                ac.localDateTime(),
                ac.status().name(),
                appointmentJpaEntity);
    }
}
