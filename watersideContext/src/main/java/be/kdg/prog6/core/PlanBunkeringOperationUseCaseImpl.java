package be.kdg.prog6.core;

import be.kdg.prog6.adapters.exceptions.BunkeringOperationDayLimitException;
import be.kdg.prog6.adapters.exceptions.VesselAlreadyLeftException;
import be.kdg.prog6.domain.BO;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.PlanBunkeringOperationCommand;
import be.kdg.prog6.ports.in.PlanBunkeringOperationUseCase;
import be.kdg.prog6.ports.out.FindSOPort;
import be.kdg.prog6.ports.out.SendShipmentOrderFulfilledPort;
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
    private final SendShipmentOrderFulfilledPort sendShipmentOrderFulfilledPort;

    public PlanBunkeringOperationUseCaseImpl(FindSOPort findSOPort, UpdateSOPort updateSOPort, SendShipmentOrderFulfilledPort sendShipmentOrderFulfilledPort) {
        this.findSOPort = findSOPort;
        this.updateSOPort = updateSOPort;
        this.sendShipmentOrderFulfilledPort = sendShipmentOrderFulfilledPort;
    }

    @Override
    @Transactional
    public void planBO(PlanBunkeringOperationCommand command) {
        if (findSOPort.findAllShipmentOrderByBunkeringOperationDate(command.date()).size() == BO.DAY_LIMIT) {
            throw new BunkeringOperationDayLimitException("Bunkering operation day limit exceeded. Pick another date.");
        }
        ShipmentOrder shipmentOrder = findSOPort.findShipmentOrderByVesselNumber(command.vesselNumber());

        if (shipmentOrder.getShipmentStatus() == ShipmentOrder.ShipmentStatus.LEFT_PORT) {
            throw new VesselAlreadyLeftException("Vessel %s already left the site".formatted(command.vesselNumber()));
        }

        shipmentOrder.scheduleBO(command.date());
        updateSOPort.updateShipmentOrder(shipmentOrder);
        log.info("Bunkering operation for vessel {} is scheduled for {} ", command.vesselNumber(), command.date());

        if (shipmentOrder.canVesselLeave()) {
            shipmentOrder.leave();
            shipmentOrder = updateSOPort.updateShipmentOrder(shipmentOrder);
            sendShipmentOrderFulfilledPort.deductMaterialFromWarehouse(shipmentOrder.getPoReferenceNumber());
            log.info("Ship %s left site".formatted(shipmentOrder.getVesselNumber()));
        }
    }
}
