package be.kdg.prog6.adapter.out.db.payloadActivity;

import be.kdg.prog6.adapter.out.db.warehouse.WarehouseJpaEntity;
import be.kdg.prog6.adapter.out.db.warehouse.WarehouseJpaRepository;
import be.kdg.prog6.domain.OrderLine;
import be.kdg.prog6.domain.PayloadActivity;
import be.kdg.prog6.domain.PurchaseOrder;
import be.kdg.prog6.port.out.PayloadCommand;
import be.kdg.prog6.port.out.PayloadRecordSavedPort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PayloadActivityAdapter implements PayloadRecordSavedPort {

    private final PayloadActivityJpaRepository payloadActivityJpaRepository;

    public PayloadActivityAdapter(PayloadActivityJpaRepository payloadActivityJpaRepository) {
        this.payloadActivityJpaRepository = payloadActivityJpaRepository;
    }

    @Override
    public void savePayloadRecord(PayloadCommand payloadCommand) {
        payloadActivityJpaRepository.save(fromCommand(payloadCommand));
    }

    @Override
    public void saveMultiplePayloadRecords(List<PayloadCommand> payloadCommands) {
        payloadActivityJpaRepository.saveAll(payloadCommands
                .stream()
                .map(this::fromCommand)
                .collect(Collectors.toList()));
    }

    private PayloadActivityJpaEntity fromCommand(PayloadCommand payloadCommand) {
        return new PayloadActivityJpaEntity(
                payloadCommand.activityType().name(),
                payloadCommand.amount(),
                payloadCommand.timestamp(),
                new WarehouseJpaEntity(payloadCommand.warehouseId())
        );
    }


}
