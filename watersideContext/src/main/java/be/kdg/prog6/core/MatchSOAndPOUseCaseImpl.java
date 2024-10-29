package be.kdg.prog6.core;

import be.kdg.prog6.adapters.exceptions.VesselAlreadyLeftException;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.MatchSOAndPOUseCase;
import be.kdg.prog6.ports.out.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchSOAndPOUseCaseImpl implements MatchSOAndPOUseCase {

    private static final Logger log = LoggerFactory.getLogger(MatchSOAndPOUseCaseImpl.class);
    private final FindSOPort findSOPort;
    private final UpdateSOPort updateSOPort;
    private final SendMatchingEventPort sendMatchingEventPort;
    private final SendShipmentOrderFulfilledPort sendShipmentOrderFulfilledPort;

    public MatchSOAndPOUseCaseImpl(FindSOPort findSOPort, UpdateSOPort updateSOPort, SendMatchingEventPort sendMatchingEventPort, SendShipmentOrderFulfilledPort sendShipmentOrderFulfilledPort) {
        this.findSOPort = findSOPort;
        this.updateSOPort = updateSOPort;
        this.sendMatchingEventPort = sendMatchingEventPort;
        this.sendShipmentOrderFulfilledPort = sendShipmentOrderFulfilledPort;
    }

    @Override
    @Transactional
    public void matchSOAndPO(String vesselNumber) {
        ShipmentOrder shipmentOrder = findSOPort.findShipmentOrderByVesselNumber(vesselNumber);
        shipmentOrder.didVesselLeave();

        shipmentOrder.matchPurchaseOrder();
        updateSOPort.updateShipmentOrder(shipmentOrder);
        sendMatchingEventPort.sendMatchingEvent(shipmentOrder.getPoReferenceNumber());
        log.info("Matched SO and PO with vessel number {}", vesselNumber);

        if (shipmentOrder.canVesselLeave()) {
            shipmentOrder.leave();
            shipmentOrder = updateSOPort.updateShipmentOrder(shipmentOrder);
            sendShipmentOrderFulfilledPort.deductMaterialFromWarehouse(shipmentOrder.getPoReferenceNumber());
            log.info("Ship %s left site".formatted(shipmentOrder.getVesselNumber()));
        }
    }
}
