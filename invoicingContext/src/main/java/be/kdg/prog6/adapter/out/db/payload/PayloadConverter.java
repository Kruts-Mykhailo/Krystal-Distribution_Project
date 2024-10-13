package be.kdg.prog6.adapter.out.db.payload;

import be.kdg.prog6.adapter.out.db.warehouse.WarehouseJpaEntity;
import be.kdg.prog6.domain.Payload;

public class PayloadConverter {
    public static PayloadJpaEntity toJpa(Payload payload, WarehouseJpaEntity warehouseJpaEntity) {
        return new PayloadJpaEntity(
                payload.getTons(),
                payload.getDeliveryDate(),
                warehouseJpaEntity
        );
    }

    public static Payload toPayload(PayloadJpaEntity payloadJpaEntity) {
        return new Payload(
                payloadJpaEntity.getArrivalDate(),
                payloadJpaEntity.getTons()
        );
    }
}
