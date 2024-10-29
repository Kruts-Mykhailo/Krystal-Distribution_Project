package be.kdg.prog6.core;

import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.ViewShipmentArrivalsUseCase;
import be.kdg.prog6.ports.out.FindSOPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewShipmentArrivalsUseCaseImpl implements ViewShipmentArrivalsUseCase {

    private final FindSOPort findSOPort;

    public ViewShipmentArrivalsUseCaseImpl(FindSOPort findSOPort) {
        this.findSOPort = findSOPort;
    }

    @Override
    @Transactional
    public List<ShipmentOrder> getAllShipmentArrivals() {
        return findSOPort.findAllWithStatusNotIn(ShipmentOrder.ShipmentStatus.LEFT_PORT);
    }
}
