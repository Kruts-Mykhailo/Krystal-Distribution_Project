package be.kdg.prog6.adapter.out.db.payloadActivity;

import be.kdg.prog6.adapter.exceptions.PayloadActivityNotFoundException;
import be.kdg.prog6.adapter.out.db.warehouse.WarehouseJpaEntity;
import be.kdg.prog6.domain.PayloadActivity;
import be.kdg.prog6.domain.WarehouseNumber;
import be.kdg.prog6.port.out.PayloadActivityFoundPort;
import be.kdg.prog6.port.out.PayloadActivityUpdatedPort;
import be.kdg.prog6.port.out.PayloadActivitySavedPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class PayloadActivityAdapter implements PayloadActivitySavedPort, PayloadActivityUpdatedPort, PayloadActivityFoundPort {

    private final PayloadActivityJpaRepository payloadActivityJpaRepository;

    public PayloadActivityAdapter(PayloadActivityJpaRepository payloadActivityJpaRepository) {
        this.payloadActivityJpaRepository = payloadActivityJpaRepository;
    }

    @Override
    public void savePayloadActivity(PayloadActivity payloadActivity, WarehouseNumber warehouseNumber) {
        payloadActivityJpaRepository.save(PayloadConverter.toJpaEntity(
                payloadActivity,
                new WarehouseJpaEntity(warehouseNumber.number())));
    }


    @Override
    public void updateZeroWeightActivity(PayloadActivity payloadActivity, WarehouseNumber warehouseNumber) {
        PayloadActivityJpaEntity payloadActivityJpaEntity = payloadActivityJpaRepository
                .findFirstByWarehouseAndAmountOrderByRecordTimeAsc(
                        new WarehouseJpaEntity(warehouseNumber.number()),
                        0.0
                ).orElseThrow(() -> new PayloadActivityNotFoundException("Payload activity not found"));
        payloadActivityJpaEntity.setAmount(payloadActivity.getAmount());
        payloadActivityJpaRepository.save(payloadActivityJpaEntity);
    }

    @Override
    public Optional<PayloadActivity> getFirstZeroWeightActivity(WarehouseNumber number, LocalDateTime arrivalDateTime) {
        return payloadActivityJpaRepository.findFirstByWarehouseAndAmountAndRecordTimeOrderByRecordTimeAsc(
                    new WarehouseJpaEntity(number.number()),
                    0.0,
                    arrivalDateTime
                ).map(PayloadConverter::toPayloadActivity);
    }
}
