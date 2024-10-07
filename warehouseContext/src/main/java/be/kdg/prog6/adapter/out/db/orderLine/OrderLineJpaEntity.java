package be.kdg.prog6.adapter.out.db.orderLine;


import be.kdg.prog6.adapter.out.db.purchaseOrder.PurchaseOrderJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "order_lines", catalog = "warehouse")
public class OrderLineJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String materialType;

    @Column
    private String uom;

    @Column
    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    private PurchaseOrderJpaEntity purchaseOrder;

    public OrderLineJpaEntity(PurchaseOrderJpaEntity purchaseOrder, Double amount, String uom, String materialType) {
        this.purchaseOrder = purchaseOrder;
        this.amount = amount;
        this.uom = uom;
        this.materialType = materialType;
    }

    public OrderLineJpaEntity() {
    }
}
