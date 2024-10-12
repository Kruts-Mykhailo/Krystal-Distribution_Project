package be.kdg.prog6.core;

import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.PlanBunkeringOperationUseCase;
import be.kdg.prog6.ports.out.FindSOPort;
import be.kdg.prog6.ports.out.SaveSOPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class PlanBunkeringOperationUseCaseImpl implements PlanBunkeringOperationUseCase {

    private static final Logger log = LoggerFactory.getLogger(PlanBunkeringOperationUseCaseImpl.class);
    private final FindSOPort findSOPort;
    private final SaveSOPort saveSOPort;

    public PlanBunkeringOperationUseCaseImpl(FindSOPort findSOPort, SaveSOPort saveSOPort) {
        this.findSOPort = findSOPort;
        this.saveSOPort = saveSOPort;
    }

    @Override
    public void planBO(LocalDate localDate, String vesselNumber) {
        ShipmentOrder shipmentOrder = findSOPort.findShipmentOrderByVesselNumber(vesselNumber);
        shipmentOrder.scheduleBO(localDate);
        saveSOPort.saveSO(shipmentOrder);
        log.info("Bunkering operation for vessel {} is scheduled for {} ", vesselNumber, localDate);
    }
}
