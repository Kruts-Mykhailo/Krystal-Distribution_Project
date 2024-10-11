package be.kdg.prog6.core;

import be.kdg.prog6.domain.PO;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.MatchSOAndPOUseCase;
import be.kdg.prog6.ports.out.FindPOPort;
import be.kdg.prog6.ports.out.FindSOPort;
import be.kdg.prog6.ports.out.SavePOPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class MatchSOAndPOUseCaseImpl implements MatchSOAndPOUseCase {

    private static final Logger log = LoggerFactory.getLogger(MatchSOAndPOUseCaseImpl.class);
    private final FindSOPort findSOPort;
    private final FindPOPort findPOPort;
    private final SavePOPort savePOPort;

    public MatchSOAndPOUseCaseImpl(FindSOPort findSOPort, FindPOPort findPOPort, SavePOPort savePOPort) {
        this.findSOPort = findSOPort;
        this.findPOPort = findPOPort;
        this.savePOPort = savePOPort;
    }

    @Override
    @Transactional
    public void matchSOAndPO(String vesselNumber) {
        ShipmentOrder shipmentOrder = findSOPort.findShipmentOrderByVesselNumber(vesselNumber);
        PO po = findPOPort.findPOByReferenceNumber(shipmentOrder.getPoReferenceNumber());
        shipmentOrder.matchPurchaseOrder(po.orderLines());
        savePOPort.savePO(po);
        log.info("Matched SO and PO with vessel number {}", vesselNumber);
    }
}
