package be.kdg.prog6.events;

import be.kdg.prog6.domain.OrderLine;

import java.util.List;
import java.util.UUID;

public record POFulfilledEvent(List<OrderLine> orderLines, UUID sellerId, String poNumber) {
}
