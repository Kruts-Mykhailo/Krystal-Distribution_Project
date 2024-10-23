package be.kdg.prog6.core;

import be.kdg.prog6.domain.*;
import be.kdg.prog6.events.CalculateCommissionForPurchaseOrderEvent;
import be.kdg.prog6.port.in.PurchaseOrderFulfilledUseCase;
import be.kdg.prog6.port.out.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PurchaseOrderFulfilledUseCaseImpl implements PurchaseOrderFulfilledUseCase {

    private final PayloadActivitySavedPort payloadActivitySavedPort;
    private final PurchaseOrderFoundPort purchaseOrderFoundPort;
    private final PurchaseOrderUpdatedPort purchaseOrderUpdatedPort;
    private final WarehouseFoundPort warehouseFoundPort;
    private final PurchaseOrderFulfilledPort commissionInfoPort;
    private final ProjectWarehouseInfoPort projectWarehouseInfoPort;

    public PurchaseOrderFulfilledUseCaseImpl(PayloadActivitySavedPort payloadActivitySavedPort, PurchaseOrderFoundPort purchaseOrderFoundPort, PurchaseOrderUpdatedPort purchaseOrderUpdatedPort, WarehouseFoundPort warehouseFoundPort, PurchaseOrderFulfilledPort commissionInfoPort, ProjectWarehouseInfoPort projectWarehouseInfoPort) {
        this.payloadActivitySavedPort = payloadActivitySavedPort;
        this.purchaseOrderFoundPort = purchaseOrderFoundPort;
        this.purchaseOrderUpdatedPort = purchaseOrderUpdatedPort;
        this.warehouseFoundPort = warehouseFoundPort;
        this.commissionInfoPort = commissionInfoPort;
        this.projectWarehouseInfoPort = projectWarehouseInfoPort;
    }

    @Override
    public void deductMaterial(PONumber poNumber) {
        PurchaseOrder purchaseOrder = purchaseOrderFoundPort.matchByPurchaseOrderNumber(poNumber.number());

        if (purchaseOrder.isNotFilled()) {
            for (OrderLine orderLine : purchaseOrder.orderLines()) {
                Double amount = orderLine.quantity() * orderLine.uom().getMeasureCoefficient();

                Warehouse warehouse = warehouseFoundPort.getWarehouseByOwnerIdAndMaterialType(
                        purchaseOrder.sellerId(),
                        orderLine.materialType()
                );
                PayloadPurchaseEvent event = new PayloadPurchaseEvent(
                        amount,
                        LocalDateTime.now()
                );
                payloadActivitySavedPort.savePayloadActivity(event, warehouse.getWarehouseNumber());
                projectWarehouseInfoPort.projectWarehouseCapacity(
                        warehouse.getWarehouseNumber(),
                        amount,
                        ActivityType.PURCHASE
                );
            }
            purchaseOrderUpdatedPort.update(purchaseOrder, PurchaseOrder.OrderStatus.FILLED);
            commissionInfoPort.sendInfoForCommission(new CalculateCommissionForPurchaseOrderEvent(
                    purchaseOrder.orderLines(),
                    purchaseOrder.sellerId().id(),
                    purchaseOrder.poNumber().number()
            ));
        }



    }
}
