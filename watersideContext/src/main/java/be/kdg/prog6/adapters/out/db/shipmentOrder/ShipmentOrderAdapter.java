package be.kdg.prog6.adapters.out.db.shipmentOrder;

import be.kdg.prog6.adapters.exceptions.ShipmentOrderNotFoundException;
import be.kdg.prog6.adapters.out.db.shipmentOrderLine.ShipmentOrderLineConverter;
import be.kdg.prog6.adapters.out.db.shipmentOrderLine.ShipmentOrderLineJpaEntityRepository;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.out.FindSOPort;
import be.kdg.prog6.ports.out.SaveSOPort;
import be.kdg.prog6.ports.out.UpdateSOPort;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ShipmentOrderAdapter implements SaveSOPort, FindSOPort, UpdateSOPort {

    private final ShipmentOrderJpaEntityRepository soRepository;
    private final ShipmentOrderLineJpaEntityRepository shipmentOrderLineRepository;

    public ShipmentOrderAdapter(ShipmentOrderJpaEntityRepository soRepository, ShipmentOrderLineJpaEntityRepository shipmentOrderLineRepository) {
        this.soRepository = soRepository;
        this.shipmentOrderLineRepository = shipmentOrderLineRepository;
    }


    @Override
    public void saveSO(ShipmentOrder shipmentOrder) {
        ShipmentOrderJpaEntity shipmentOrderJpaEntity = soRepository.save(ShipmentOrderConverter.toShipmentOrderJpaEntity(shipmentOrder));
        shipmentOrderLineRepository.saveAll(
                ShipmentOrderLineConverter.toEntityList(
                        shipmentOrder.getOrderLines(),
                        shipmentOrderJpaEntity));
    }

    @Override
    public ShipmentOrder findShipmentOrderByVesselNumber(String vesselNumber) {
        return ShipmentOrderConverter.toShipmentOrderEntity(soRepository.findOrderByVesselNumberFetched(vesselNumber)
                .orElseThrow(() -> new ShipmentOrderNotFoundException("No shipment order found for vessel number " + vesselNumber)));
    }

    @Override
    public List<ShipmentOrder> findShipmentOrderByBunkeringOperationDate(LocalDate date) {
        return soRepository.findAllByBunkeringOperationDate(date)
                .stream()
                .map(ShipmentOrderConverter::toShipmentOrderEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipmentOrder> findAllWithoutIO() {
        return soRepository.findAllByIOFetched()
                .stream()
                .map(ShipmentOrderConverter::toShipmentOrderEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<ShipmentOrder> findAllOnSite() {
        return soRepository.findAllByShipmentStatusIsNot(ShipmentOrder.ShipmentStatus.LEFT_PORT.name())
                .stream()
                .map(ShipmentOrderConverter::toShipmentOrderEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void updateShipmentOrder(ShipmentOrder shipmentOrder) {
        soRepository.save(ShipmentOrderConverter.toShipmentOrderJpaEntity(shipmentOrder));
    }
}
