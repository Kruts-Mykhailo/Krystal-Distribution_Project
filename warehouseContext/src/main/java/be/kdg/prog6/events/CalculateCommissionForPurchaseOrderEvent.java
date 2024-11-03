package be.kdg.prog6.events;

import be.kdg.prog6.domain.OrderLine;

import java.util.List;
import java.util.UUID;

public record CalculateCommissionForPurchaseOrderEvent(List<CommissionOrderLine> orderLines, UUID sellerId, String poNumber) {
    public record CommissionOrderLine(String materialType, Double quantity, String uom){

    }
}
