package be.kdg.prog6.adapter.out.db.payloadActivity;

import be.kdg.prog6.domain.ActivityType;
import be.kdg.prog6.domain.PayloadActivity;
import be.kdg.prog6.domain.PayloadDelivery;
import be.kdg.prog6.domain.PayloadPurchase;

public class PayloadConverter {

    public static PayloadActivity fromJpa(PayloadActivityJpaEntity payloadActivityJpaEntity) {
        if (ActivityType.valueOf(payloadActivityJpaEntity.getActivityType()) == ActivityType.DELIVERY) {
            return new PayloadDelivery(payloadActivityJpaEntity.getAmount(), payloadActivityJpaEntity.getRecordTime());
        }
        return new PayloadPurchase(payloadActivityJpaEntity.getAmount(), payloadActivityJpaEntity.getRecordTime());
    }

    public static PayloadActivityJpaEntity toJpa(PayloadActivity payloadActivity) {
        return new PayloadActivityJpaEntity(
                payloadActivity.getActivityType().name(),
                payloadActivity.getAmount(),
                payloadActivity.getEventDateTime()
        );
    }



}
