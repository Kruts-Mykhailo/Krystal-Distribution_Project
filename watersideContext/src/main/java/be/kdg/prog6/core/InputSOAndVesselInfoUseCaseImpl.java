package be.kdg.prog6.core;

import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.InputSOAndVesselInfoUseCase;
import be.kdg.prog6.ports.out.SaveSOPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class InputSOAndVesselInfoUseCaseImpl implements InputSOAndVesselInfoUseCase {

    private static final Logger log = LoggerFactory.getLogger(InputSOAndVesselInfoUseCaseImpl.class);
    private final SaveSOPort saveSOPort;

    public InputSOAndVesselInfoUseCaseImpl(SaveSOPort saveSOPort) {
        this.saveSOPort = saveSOPort;
    }

    @Override
    @Transactional
    public void inputInformation(ShipmentOrder shipmentOrder) {
        saveSOPort.saveSO(shipmentOrder);
        log.info("Ship {} has arrived", shipmentOrder.getVesselNumber());
    }
}
