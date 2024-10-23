package be.kdg.prog6.adapter.out.db.payloadActivity;

import be.kdg.prog6.adapter.out.db.warehouse.WarehouseJpaEntity;
import be.kdg.prog6.domain.ActivityType;
import be.kdg.prog6.domain.PayloadActivity;
import be.kdg.prog6.domain.PayloadDeliveryEvent;
import be.kdg.prog6.domain.PayloadPurchaseEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PayloadConverter {

    public static PayloadActivity toPayloadActivity(PayloadActivityJpaEntity payloadActivityJpaEntity) {
        if (ActivityType.valueOf(payloadActivityJpaEntity.getActivityType()) == ActivityType.DELIVERY) {
            return new PayloadDeliveryEvent(payloadActivityJpaEntity.getAmount(), payloadActivityJpaEntity.getRecordTime());
        }
        return new PayloadPurchaseEvent(payloadActivityJpaEntity.getAmount(), payloadActivityJpaEntity.getRecordTime());
    }
    public static PayloadActivityJpaEntity toJpaEntity(PayloadActivity payloadActivity, WarehouseJpaEntity warehouse) {
        return new PayloadActivityJpaEntity(
                payloadActivity.getActivityType().name(),
                payloadActivity.getAmount(),
                payloadActivity.getEventDateTime(),
                warehouse
        );
    }

    public static List<PayloadActivity> toPayloadActivities(List<PayloadActivityJpaEntity> payloadActivityJpaEntities) {
        if (payloadActivityJpaEntities == null || payloadActivityJpaEntities.isEmpty()) {
            return new ArrayList<>();
        }
        return payloadActivityJpaEntities.stream()
                .map(PayloadConverter::toPayloadActivity)
                .collect(Collectors.toList());
    }

}
