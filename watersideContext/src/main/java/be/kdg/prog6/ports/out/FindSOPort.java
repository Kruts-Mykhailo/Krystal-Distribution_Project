package be.kdg.prog6.ports.out;

import be.kdg.prog6.domain.ShipmentOrder;

import java.time.LocalDate;
import java.util.List;

public interface FindSOPort {

    ShipmentOrder getByVesselNumberAndNotStatus(String vesselNumber, ShipmentOrder.ShipmentStatus statusNot);
    List<ShipmentOrder> findAllShipmentOrderByBunkeringOperationDate(LocalDate date);
    List<ShipmentOrder> findAllWithoutIO();
    List<ShipmentOrder> findAllWithStatusNotIn(ShipmentOrder.ShipmentStatus status);
}
