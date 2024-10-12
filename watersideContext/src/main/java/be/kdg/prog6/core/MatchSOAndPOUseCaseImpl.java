package be.kdg.prog6.core;

import be.kdg.prog6.adapters.exceptions.MatchSOAndPOFailedException;
import be.kdg.prog6.domain.PO;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.MatchSOAndPOUseCase;
import be.kdg.prog6.ports.out.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MatchSOAndPOUseCaseImpl implements MatchSOAndPOUseCase {

    private static final Logger log = LoggerFactory.getLogger(MatchSOAndPOUseCaseImpl.class);
    private final FindSOPort findSOPort;
    private final FindPOPort findPOPort;
    private final UpdateSOPort updateSOPort;

    public MatchSOAndPOUseCaseImpl(FindSOPort findSOPort, FindPOPort findPOPort, UpdateSOPort updateSOPort) {
        this.findSOPort = findSOPort;
        this.findPOPort = findPOPort;
        this.updateSOPort = updateSOPort;
    }

    @Override
    @Transactional
    public void matchSOAndPO(String vesselNumber) {
        ShipmentOrder shipmentOrder = findSOPort.findShipmentOrderByVesselNumber(vesselNumber);
        PO po = findPOPort.findPOByReferenceNumber(shipmentOrder.getPoReferenceNumber());
        shipmentOrder.matchPurchaseOrder(po.orderLines());
        if (!shipmentOrder.getMatchedWithPO()) {
            throw new MatchSOAndPOFailedException("Failed to match SO and PO for PO: %s".formatted(po.poNumber()));
        }
        updateSOPort.updateShipmentOrder(shipmentOrder);
        log.info("Matched SO and PO with vessel number {}", vesselNumber);
    }
}
