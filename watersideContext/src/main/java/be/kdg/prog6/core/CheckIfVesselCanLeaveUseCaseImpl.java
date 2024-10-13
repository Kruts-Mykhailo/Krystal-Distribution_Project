package be.kdg.prog6.core;

import be.kdg.prog6.domain.PO;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.events.CommissionEvent;
import be.kdg.prog6.ports.in.CheckIfVesselCanLeaveUseCase;
import be.kdg.prog6.ports.out.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CheckIfVesselCanLeaveUseCaseImpl implements CheckIfVesselCanLeaveUseCase {

    private final FindSOPort findSOPort;
    private final FindPOPort findPOPort;
    private final UpdateSOPort updateSOPort;
    private final ShipmentOrderFulfilledPort shipmentOrderFulfilledPort;
    private final SendCommissionInfoPort sendCommissionInfoPort;

    public CheckIfVesselCanLeaveUseCaseImpl(FindSOPort findSOPort, FindPOPort findPOPort, UpdateSOPort updateSOPort, ShipmentOrderFulfilledPort shipmentOrderFulfilledPort, SendCommissionInfoPort sendCommissionInfoPort) {
        this.findSOPort = findSOPort;
        this.findPOPort = findPOPort;
        this.updateSOPort = updateSOPort;
        this.shipmentOrderFulfilledPort = shipmentOrderFulfilledPort;
        this.sendCommissionInfoPort = sendCommissionInfoPort;
    }

    @Override
    @Transactional
    public ShipmentOrder checkIfVesselCanLeave(String vesselNumber) {

        ShipmentOrder shipmentOrder = findSOPort.findShipmentOrderByVesselNumber(vesselNumber);
        if (shipmentOrder.canVesselLeave()) {
            shipmentOrder.leave();
            shipmentOrder = updateSOPort.updateShipmentOrder(shipmentOrder);
            PO purchaseOrder = findPOPort.findPOByReferenceNumber(shipmentOrder.getPoReferenceNumber());
            sendCommissionInfoPort.sendInfoForCommission(new CommissionEvent(purchaseOrder.orderLines(), purchaseOrder.sellerId()));
            shipmentOrderFulfilledPort.deductMaterialFromWarehouse(shipmentOrder.getPoReferenceNumber());
        }

        return shipmentOrder;
    }
}
