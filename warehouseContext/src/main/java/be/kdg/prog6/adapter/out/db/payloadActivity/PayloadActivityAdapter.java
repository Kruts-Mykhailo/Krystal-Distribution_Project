package be.kdg.prog6.adapter.out.db.payloadActivity;

import be.kdg.prog6.adapter.exceptions.PayloadActivityNotFoundException;
import be.kdg.prog6.adapter.out.db.warehouse.WarehouseJpaEntity;
import be.kdg.prog6.adapter.out.db.warehouse.WarehouseJpaRepository;
import be.kdg.prog6.domain.PayloadActivity;
import be.kdg.prog6.domain.WarehouseNumber;
import be.kdg.prog6.port.out.PayloadActivityUpdatedPort;
import be.kdg.prog6.port.out.PayloadActivitySavedPort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PayloadActivityAdapter implements PayloadActivitySavedPort, PayloadActivityUpdatedPort {

    private final PayloadActivityJpaRepository payloadActivityJpaRepository;
    private final WarehouseJpaRepository warehouseJpaRepository;

    public PayloadActivityAdapter(PayloadActivityJpaRepository payloadActivityJpaRepository, WarehouseJpaRepository warehouseJpaRepository) {
        this.payloadActivityJpaRepository = payloadActivityJpaRepository;
        this.warehouseJpaRepository = warehouseJpaRepository;
    }

    @Override
    public void savePayloadActivity(PayloadActivity payloadActivity, WarehouseNumber warehouseNumber) {
        PayloadActivityJpaEntity payloadActivityJpa = PayloadConverter.toJpa(payloadActivity);
        WarehouseJpaEntity warehouseJpaEntity = warehouseJpaRepository.getReferenceById(warehouseNumber.number());

        payloadActivityJpa.setWarehouse(warehouseJpaEntity);

        payloadActivityJpaRepository.save(payloadActivityJpa);
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
