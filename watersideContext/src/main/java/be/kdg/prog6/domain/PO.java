package be.kdg.prog6.domain;

import be.kdg.prog6.events.PurchaseOrderReceivedEvent;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record PO (UUID sellerId, List<OrderLine> orderLines, String poNumber, POStatus poStatus){

    public static PO fromEvent(PurchaseOrderReceivedEvent receivedEvent) {
        return new PO(
                receivedEvent.getPurchaseOrder().getSellerParty().getUuid(),
                receivedEvent.getPurchaseOrder().getOrderLines()
                        .stream()
                        .map(ol -> new OrderLine(
                                MaterialType.fromCode(ol.getMaterialType()),
                                (double) ol.getQuantity(),
                                UOM.fromCode(ol.getUom())
                        ))
                        .collect(Collectors.toList()),
                receivedEvent.getPurchaseOrder().getPoNumber(),
                POStatus.OUTSTANDING
        );
    }
    public enum POStatus{
        OUTSTANDING, MATCHED
    }
}
