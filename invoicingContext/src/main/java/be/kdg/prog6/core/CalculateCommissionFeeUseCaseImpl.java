package be.kdg.prog6.core;

import be.kdg.prog6.domain.*;
import be.kdg.prog6.events.CommissionEvent;
import be.kdg.prog6.port.in.CalculateCommissionFeeUseCase;
import be.kdg.prog6.port.out.CommissionFeeFoundPort;
import be.kdg.prog6.port.out.SaveCommissionFeePort;
import be.kdg.prog6.port.out.UpdateWarehouseStoragePort;
import be.kdg.prog6.port.out.WarehouseFoundPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CalculateCommissionFeeUseCaseImpl implements CalculateCommissionFeeUseCase {

    private static final Logger log = LoggerFactory.getLogger(CalculateCommissionFeeUseCaseImpl.class);
    private final WarehouseFoundPort warehouseFoundPort;
    private final UpdateWarehouseStoragePort warehouseStoragePort;
    private final SaveCommissionFeePort saveCommissionFeePort;
    private final CommissionFeeFoundPort commissionFeeFoundPort;


    public CalculateCommissionFeeUseCaseImpl(WarehouseFoundPort warehouseFoundPort, UpdateWarehouseStoragePort warehouseStoragePort, SaveCommissionFeePort saveCommissionFeePort, CommissionFeeFoundPort commissionFeeFoundPort) {
        this.warehouseFoundPort = warehouseFoundPort;
        this.warehouseStoragePort = warehouseStoragePort;
        this.saveCommissionFeePort = saveCommissionFeePort;
        this.commissionFeeFoundPort = commissionFeeFoundPort;
    }

    @Override
    @Transactional
    public void calculate(CommissionEvent commissionEvent) {
        Optional<CommissionFee> existingCommissionFee = commissionFeeFoundPort.findByPoNumber(new PONumber(commissionEvent.poNumber()));
        if (existingCommissionFee.isEmpty()) {
            Double value = commissionEvent
                    .orderLines()
                    .stream()
                    .mapToDouble(orderLine -> {
                        Double tons = UOM.fromCode(orderLine.uom()).getMeasureCoefficient() * orderLine.quantity();
                        Warehouse warehouse = warehouseFoundPort.getBySellerIdAndMaterialType(
                                new Seller.SellerId(commissionEvent.sellerId()),
                                MaterialType.valueOf(orderLine.materialType())
                        );
                        warehouse.removeOldestPayload(tons);
                        warehouseStoragePort.update(warehouse);
                        return warehouse.calculateCommissionFee(tons);})
                    .sum();

            CommissionFee commissionFee = new CommissionFee(
                    new Seller.SellerId(commissionEvent.sellerId()),
                    value,
                    new PONumber(commissionEvent.poNumber()),
                    LocalDateTime.now(),
                    CommissionFee.FeeStatus.CREATED);

            saveCommissionFeePort.saveCommissionFee(commissionFee);
            log.info("Calculated commission fee for purchase order {}", commissionFee.poNumber());
        }
    }
}
