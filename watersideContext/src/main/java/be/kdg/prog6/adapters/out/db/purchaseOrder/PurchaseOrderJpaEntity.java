package be.kdg.prog6.adapters.out.db.purchaseOrder;


import be.kdg.prog6.adapters.out.db.orderLine.OrderLineJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "purchase_orders", catalog = "waterside")
public class PurchaseOrderJpaEntity {

    @Id
    private String poNumber;

    @Column
    private UUID sellerId;

    @Column
    private String orderStatus;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderLineJpaEntity> orderLines;

    public PurchaseOrderJpaEntity() {
    }

    public PurchaseOrderJpaEntity(String poNumber, UUID sellerId, String orderStatus) {
        this.poNumber = poNumber;
        this.sellerId = sellerId;
        this.orderStatus = orderStatus;
    }

    public PurchaseOrderJpaEntity(String poNumber) {
        this.poNumber = poNumber;
    }
}
