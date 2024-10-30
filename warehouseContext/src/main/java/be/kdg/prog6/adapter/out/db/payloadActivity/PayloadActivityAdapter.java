package be.kdg.prog6.adapter.out.db.payloadActivity;

import be.kdg.prog6.adapter.exceptions.PayloadActivityNotFoundException;
import be.kdg.prog6.adapter.out.db.warehouse.WarehouseJpaEntity;
import be.kdg.prog6.domain.PayloadActivity;
import be.kdg.prog6.domain.WarehouseNumber;
import be.kdg.prog6.port.out.PayloadActivityFoundPort;
import be.kdg.prog6.port.out.PayloadActivityUpdatedPort;
import be.kdg.prog6.port.out.PayloadActivitySavedPort;
import org.springframework.stereotype.Component;

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
    public void updateWeight(PayloadActivity payloadActivity, WarehouseNumber warehouseNumber, Double netWeight) {
        PayloadActivityJpaEntity payloadActivityJpaEntity = payloadActivityJpaRepository
                .findFirstByWarehouseAndAmountAndRecordTime(
                        warehouseNumber.number(),
                        payloadActivity.payload(),
                        payloadActivity.getEventDateTime())
                .stream()
                .findFirst()
                .orElseThrow(() -> new PayloadActivityNotFoundException("PayloadActivity not found"));

        payloadActivityJpaEntity.setAmount(netWeight);
        payloadActivityJpaRepository.save(payloadActivityJpaEntity);
    }

}
