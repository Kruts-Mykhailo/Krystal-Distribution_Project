package be.kdg.prog6.adapters.out.db.shipmentOrder;

import be.kdg.prog6.adapters.exceptions.ShipmentOrderNotFoundException;
import be.kdg.prog6.adapters.out.db.shipmentOrderLine.ShipmentOrderLineConverter;
import be.kdg.prog6.adapters.out.db.shipmentOrderLine.ShipmentOrderLineJpaEntityRepository;
import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.out.FindSOPort;
import be.kdg.prog6.ports.out.SaveSOPort;
import org.springframework.stereotype.Component;

@Component
public class ShipmentOrderAdapter implements SaveSOPort, FindSOPort {

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
}
