package be.kdg.prog6.core;

import be.kdg.prog6.adapters.exceptions.MatchSOAndPOFailedException;
import be.kdg.prog6.domain.PO;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.MatchSOAndPOUseCase;
import be.kdg.prog6.ports.out.FindPOPort;
import be.kdg.prog6.ports.out.FindSOPort;
import be.kdg.prog6.ports.out.SavePOPort;
import be.kdg.prog6.ports.out.SaveSOPort;
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
    private final SaveSOPort saveSOPort;

    public MatchSOAndPOUseCaseImpl(FindSOPort findSOPort, FindPOPort findPOPort, SaveSOPort saveSOPort) {
        this.findSOPort = findSOPort;
        this.findPOPort = findPOPort;
        this.saveSOPort = saveSOPort;
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
        saveSOPort.saveSO(shipmentOrder);
        log.info("Matched SO and PO with vessel number {}", vesselNumber);
    }
}
