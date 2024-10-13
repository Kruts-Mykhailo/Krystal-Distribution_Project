package be.kdg.prog6.events;

import be.kdg.prog6.domain.OrderLine;

import java.util.List;
import java.util.UUID;

public record CommissionEvent(List<OrderLine> orderLines, UUID sellerId) {
}
