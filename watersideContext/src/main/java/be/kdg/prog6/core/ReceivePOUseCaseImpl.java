package be.kdg.prog6.core;

import be.kdg.prog6.domain.PO;
import be.kdg.prog6.ports.in.ReceivePOUseCase;
import be.kdg.prog6.ports.out.SavePOPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReceivePOUseCaseImpl implements ReceivePOUseCase {

    private static final Logger log = LoggerFactory.getLogger(ReceivePOUseCaseImpl.class);
    private final SavePOPort savePOPort;

    public ReceivePOUseCaseImpl(SavePOPort savePOPort) {
        this.savePOPort = savePOPort;
    }

    @Override
    @Transactional
    public void receivePO(PO purchaseOrder) {
        savePOPort.savePO(purchaseOrder);
        log.info("Received PO: {}", purchaseOrder.poNumber());
    }
}
