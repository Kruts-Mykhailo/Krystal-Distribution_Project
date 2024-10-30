package be.kdg.prog6.core;

import be.kdg.prog6.domain.*;
import be.kdg.prog6.events.CalculateCommissionForPurchaseOrderEvent;
import be.kdg.prog6.events.WarehouseCapacityChangeEvent;
import be.kdg.prog6.port.in.PurchaseOrderFulfilledUseCase;
import be.kdg.prog6.port.out.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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
    @Transactional
    public void deductMaterial(PONumber poNumber) {
        PurchaseOrder purchaseOrder = purchaseOrderFoundPort.getByPurchaseOrderNumber(poNumber.number());

        if (purchaseOrder.isNotFilled()) {
            for (OrderLine orderLine : purchaseOrder.orderLines()) {
                Double amount = orderLine.getAmount();

                Warehouse warehouse = warehouseFoundPort.getWarehouseByOwnerIdAndMaterialType(
                        purchaseOrder.getSeller().getSellerId(),
                        orderLine.materialType()
                );
                PayloadPurchase event = new PayloadPurchase(
                        amount,
                        LocalDateTime.now()
                );
                payloadActivitySavedPort.savePayloadActivity(event, warehouse.getWarehouseNumber());
                projectWarehouseInfoPort.projectWarehouseCapacity(
                        new WarehouseCapacityChangeEvent(
                                warehouse.getWarehouseNumber().number(),
                                amount,
                                ActivityType.PURCHASE.name())
                );
            }
            purchaseOrder.fillOrder();
            purchaseOrderUpdatedPort.updateStatus(purchaseOrder);
            commissionInfoPort.sendInfoForCommission(new CalculateCommissionForPurchaseOrderEvent(
                    purchaseOrder.orderLines(),
                    purchaseOrder.getSeller().getSellerId().id(),
                    purchaseOrder.poNumber().number()
            ));
        }



    }
}
