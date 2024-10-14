package be.kdg.prog6.core;

import be.kdg.prog6.domain.*;
import be.kdg.prog6.events.POFulfilledEvent;
import be.kdg.prog6.port.in.PurchaseOrderFulfilledUseCase;
import be.kdg.prog6.port.out.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderFulfilledUseCaseImpl implements PurchaseOrderFulfilledUseCase {

    private final PayloadRecordSavedPort payloadRecordSavedPort;
    private final PurchaseOrderFoundPort purchaseOrderFoundPort;
    private final PurchaseOrderUpdatedPort purchaseOrderUpdatedPort;
    private final WarehouseFoundPort warehouseFoundPort;
    private final PurchaseOrderFulfilledPort commissionInfoPort;

    public PurchaseOrderFulfilledUseCaseImpl(PayloadRecordSavedPort payloadRecordSavedPort, PurchaseOrderFoundPort purchaseOrderFoundPort, PurchaseOrderUpdatedPort purchaseOrderUpdatedPort, WarehouseFoundPort warehouseFoundPort, PurchaseOrderFulfilledPort commissionInfoPort) {
        this.payloadRecordSavedPort = payloadRecordSavedPort;
        this.purchaseOrderFoundPort = purchaseOrderFoundPort;
        this.purchaseOrderUpdatedPort = purchaseOrderUpdatedPort;
        this.warehouseFoundPort = warehouseFoundPort;
        this.commissionInfoPort = commissionInfoPort;
    }

    @Override
    public void deductMaterial(ShippingOrder shippingOrder) {
        PurchaseOrder purchaseOrder = purchaseOrderFoundPort.matchByPurchaseOrderNumber(shippingOrder.poNumber());
        List<PayloadCommand> payloadCommands = purchaseOrder.orderLines()
                .stream()
                .map(orderLine -> {
                    UUID warehouseId = warehouseFoundPort.getWarehouseIdByOwnerIdAndMaterialType(
                            purchaseOrder.sellerId(),
                            orderLine.materialType());
                    return new PayloadCommand(
                            ActivityType.PURCHASE,
                            orderLine.quantity() * orderLine.uom().getMeasureCoefficient(),
                            warehouseId,
                            LocalDateTime.now()
                    );})
                .collect(Collectors.toList());

        payloadRecordSavedPort.saveMultiplePayloadRecords(payloadCommands);
        purchaseOrderUpdatedPort.update(purchaseOrder, PurchaseOrder.OrderStatus.FILLED);
        commissionInfoPort.sendInfoForCommission(new POFulfilledEvent(
                purchaseOrder.orderLines(),
                purchaseOrder.sellerId().id(),
                purchaseOrder.poNumber()
        ));

    }
}
