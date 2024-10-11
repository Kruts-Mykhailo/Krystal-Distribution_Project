package be.kdg.prog6.adapters.out.db.shipmentOrderLine;


import be.kdg.prog6.adapters.out.db.shipmentOrder.ShipmentOrderJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "so_order_lines", catalog = "waterside")
public class ShipmentOrderLineJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String materialType;

    @Column
    private String uom;

    @Column
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private ShipmentOrderJpaEntity shipmentOrder;

    public ShipmentOrderLineJpaEntity() {
    }

    public ShipmentOrderLineJpaEntity(String materialType, String uom, Double amount, ShipmentOrderJpaEntity shipmentOrder) {
        this.materialType = materialType;
        this.uom = uom;
        this.amount = amount;
        this.shipmentOrder = shipmentOrder;
    }
}
