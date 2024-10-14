package be.kdg.prog6.core;

import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.CompleteVesselInspectionUseCase;
import be.kdg.prog6.ports.in.VesselInspectionCommand;
import be.kdg.prog6.ports.out.FindSOPort;
import be.kdg.prog6.ports.out.SendShipmentOrderFulfilledPort;
import be.kdg.prog6.ports.out.UpdateSOPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CompleteVesselInspectionUseCaseImpl implements CompleteVesselInspectionUseCase {

    private static final Logger log = LoggerFactory.getLogger(CompleteVesselInspectionUseCaseImpl.class);
    private final UpdateSOPort updateSOPort;
    private final FindSOPort findSOPort;
    private final SendShipmentOrderFulfilledPort sendShipmentOrderFulfilledPort;


    public CompleteVesselInspectionUseCaseImpl(UpdateSOPort updateSOPort, FindSOPort findSOPort, SendShipmentOrderFulfilledPort sendShipmentOrderFulfilledPort) {
        this.updateSOPort = updateSOPort;
        this.findSOPort = findSOPort;
        this.sendShipmentOrderFulfilledPort = sendShipmentOrderFulfilledPort;
    }

    @Override
    public void completeVesselInspection(VesselInspectionCommand command) {
        ShipmentOrder shipmentOrder = findSOPort.findShipmentOrderByVesselNumber(command.vesselNumber());
        shipmentOrder.completeIO(command.inspectionDate(), command.inspectorSignature());
        updateSOPort.updateShipmentOrder(shipmentOrder);
        log.info("Vessel {} was inspected by {} on {}",
                command.vesselNumber(),
                command.inspectorSignature(),
                command.inspectionDate());

        if (shipmentOrder.canVesselLeave()) {
            shipmentOrder.leave();
            shipmentOrder = updateSOPort.updateShipmentOrder(shipmentOrder);
            sendShipmentOrderFulfilledPort.deductMaterialFromWarehouse(shipmentOrder.getPoReferenceNumber());
            log.info("Ship %s left site".formatted(shipmentOrder.getVesselNumber()));
        }
    }
}
