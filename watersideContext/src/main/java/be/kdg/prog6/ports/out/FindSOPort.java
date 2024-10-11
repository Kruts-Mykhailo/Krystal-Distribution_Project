package be.kdg.prog6.ports.out;

import be.kdg.prog6.domain.ShipmentOrder;

public interface FindSOPort {

    ShipmentOrder findShipmentOrderByVesselNumber(String vesselNumber);
}
