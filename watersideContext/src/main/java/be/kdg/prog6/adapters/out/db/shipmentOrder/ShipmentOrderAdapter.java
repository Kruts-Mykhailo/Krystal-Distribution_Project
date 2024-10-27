package be.kdg.prog6.adapters.out.db.shipmentOrder;

import be.kdg.prog6.adapters.exceptions.ShipmentOrderNotFoundException;
import be.kdg.prog6.adapters.exceptions.VesselAlreadyLeftException;
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

    public ShipmentOrderAdapter(ShipmentOrderJpaEntityRepository soRepository) {
        this.soRepository = soRepository;
    }


    @Override
    public void saveSO(ShipmentOrder shipmentOrder) {
        soRepository.save(ShipmentOrderConverter.toShipmentOrderJpaEntity(shipmentOrder));

    }

    @Override
    public ShipmentOrder findShipmentOrderByVesselNumber(String vesselNumber) {
        ShipmentOrderJpaEntity shipmentOrderJpaEntity = soRepository.findOrderByVesselNumberFetched(vesselNumber)
                .orElseThrow(() -> new ShipmentOrderNotFoundException("No shipment order found for vessel number " + vesselNumber));
        return ShipmentOrderConverter.toShipmentOrderEntity(shipmentOrderJpaEntity);
    }

    @Override
    public List<ShipmentOrder> findAllShipmentOrderByBunkeringOperationDate(LocalDate date) {
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
    public ShipmentOrder updateShipmentOrder(ShipmentOrder shipmentOrder) {
        return ShipmentOrderConverter.toShipmentOrderEntity(soRepository.save(ShipmentOrderConverter.toShipmentOrderJpaEntity(shipmentOrder)));
    }
}
