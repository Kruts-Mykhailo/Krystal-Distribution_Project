package be.kdg.prog6.ports.out;

import be.kdg.prog6.domain.ShipmentOrder;

import java.time.LocalDate;
import java.util.List;

public interface FindSOPort {

    ShipmentOrder findShipmentOrderByVesselNumber(String vesselNumber);
    List<ShipmentOrder> findShipmentOrderByBunkeringOperationDate(LocalDate date);
}
