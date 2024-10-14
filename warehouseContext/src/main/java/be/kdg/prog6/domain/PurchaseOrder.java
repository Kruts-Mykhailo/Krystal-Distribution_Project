package be.kdg.prog6.domain;

import be.kdg.prog6.events.PurchaseOrderReceivedEvent;

import java.util.List;
import java.util.stream.Collectors;

public record PurchaseOrder(Seller.SellerId sellerId, List<OrderLine> orderLines, String poNumber, OrderStatus status) {

    public static PurchaseOrder fromEvent(PurchaseOrderReceivedEvent receivedEvent) {
        return new PurchaseOrder(
                new Seller.SellerId(receivedEvent.getPurchaseOrder().getSellerParty().getUuid()),
                receivedEvent.getPurchaseOrder().getOrderLines()
                        .stream()
                        .map(ol -> new OrderLine(
                                MaterialType.fromCode(ol.getMaterialType()),
                                (double) ol.getQuantity(),
                                UOM.fromCode(ol.getUom())
                        ))
                        .collect(Collectors.toList()),
                receivedEvent.getPurchaseOrder().getPoNumber(),
                PurchaseOrder.OrderStatus.OUTSTANDING
        );
    }

    public enum OrderStatus {
        OUTSTANDING, MATCHED, FILLED
    }
}
