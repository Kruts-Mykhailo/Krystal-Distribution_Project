package be.kdg.prog6.core;

import be.kdg.prog6.adapters.exceptions.BunkeringOperationDayLimitException;
import be.kdg.prog6.domain.BO;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.PlanBunkeringOperationCommand;
import be.kdg.prog6.ports.in.PlanBunkeringOperationUseCase;
import be.kdg.prog6.ports.out.FindSOPort;
import be.kdg.prog6.ports.out.UpdateSOPort;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PlanBunkeringOperationUseCaseImpl implements PlanBunkeringOperationUseCase {

    private static final Logger log = LoggerFactory.getLogger(PlanBunkeringOperationUseCaseImpl.class);
    private final FindSOPort findSOPort;
    private final UpdateSOPort updateSOPort;

    public PlanBunkeringOperationUseCaseImpl(FindSOPort findSOPort, UpdateSOPort updateSOPort) {
        this.findSOPort = findSOPort;
        this.updateSOPort = updateSOPort;
    }

    @Override
    @Transactional
    public void planBO(PlanBunkeringOperationCommand command) {
        if (findSOPort.findAllShipmentOrderByBunkeringOperationDate(command.date()).size() == BO.DAY_LIMIT) {
            throw new BunkeringOperationDayLimitException("Bunkering operation day limit exceeded. Pick another date.");
        }
        ShipmentOrder shipmentOrder = findSOPort.findShipmentOrderByVesselNumber(command.vesselNumber());
        shipmentOrder.scheduleBO(command.date());
        updateSOPort.updateShipmentOrder(shipmentOrder);
        log.info("Bunkering operation for vessel {} is scheduled for {} ", command.vesselNumber(), command.date());
    }
}
