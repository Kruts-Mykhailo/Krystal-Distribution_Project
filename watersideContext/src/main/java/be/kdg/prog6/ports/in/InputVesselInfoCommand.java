package be.kdg.prog6.ports.in;

import be.kdg.prog6.domain.OrderLine;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public record InputVesselInfoCommand(String poRefernceNumber, String vesselNumber, List<OrderLine> orderLines, String customerEnterpriseNumber, LocalDate departureDate) {
    public InputVesselInfoCommand {
        if (vesselNumber.isEmpty()) {
            throw new IllegalArgumentException("Vessel number must not be empty");
        }
        if (customerEnterpriseNumber.isEmpty()) {
            throw new IllegalArgumentException("Customer Enterprise number must not be empty");
        }
        if (orderLines.isEmpty()) {
            throw new IllegalArgumentException("Shipment Order must contain order lines");
        }
        if (departureDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Departure date must not be before current date");
        }
        if (poRefernceNumber.isEmpty()) {
            throw new IllegalArgumentException("Purchase order refernce number must not be empty");
        }
        if (!poRefernceNumber.startsWith("PO")) {
            throw new IllegalArgumentException("Purchase order refernce number must start with 'PO'");
        }

    }
}
