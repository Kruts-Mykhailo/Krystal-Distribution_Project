package be.kdg.prog6.core;

import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.CompleteVesselInspectionUseCase;
import be.kdg.prog6.ports.in.VesselInspectionCommand;
import be.kdg.prog6.ports.out.FindSOPort;
import be.kdg.prog6.ports.out.UpdateSOPort;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CompleteVesselInspectionUseCaseImpl implements CompleteVesselInspectionUseCase {

    private static final Logger log = LoggerFactory.getLogger(CompleteVesselInspectionUseCaseImpl.class);
    private final UpdateSOPort updateSOPort;
    private final FindSOPort findSOPort;


    public CompleteVesselInspectionUseCaseImpl(UpdateSOPort updateSOPort, FindSOPort findSOPort) {
        this.updateSOPort = updateSOPort;
        this.findSOPort = findSOPort;
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
    }
}
