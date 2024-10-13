package be.kdg.prog6.events;

import java.util.List;
import java.util.UUID;

public record CommissionEvent(List<CommissionOrderLine> orderLines, UUID sellerId, String poNumber) {

    public record CommissionOrderLine(String materialType, Double quantity, String uom){

    }
}
