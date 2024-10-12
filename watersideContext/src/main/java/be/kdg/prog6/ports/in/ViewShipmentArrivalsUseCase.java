package be.kdg.prog6.ports.in;

import be.kdg.prog6.domain.ShipmentOrder;

import java.util.List;

public interface ViewShipmentArrivalsUseCase {
    List<ShipmentOrder> getAllShipmentArrivals();
}
