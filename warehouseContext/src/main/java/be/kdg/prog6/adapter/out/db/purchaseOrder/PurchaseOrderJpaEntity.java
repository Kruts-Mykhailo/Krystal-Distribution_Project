package be.kdg.prog6.adapter.out.db.purchaseOrder;


import be.kdg.prog6.adapter.out.db.orderLine.OrderLineJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "purchase_orders", catalog = "warehouse")
public class PurchaseOrderJpaEntity {

    @Id
    private String poNumber;

    @Column
    private UUID sellerId;

    @Column
    private String orderStatus;

    @Column
    private LocalDateTime receivedDateTime;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderLineJpaEntity> orderLines;

    public PurchaseOrderJpaEntity() {
    }

    public PurchaseOrderJpaEntity(String poNumber, UUID sellerId, String orderStatus, LocalDateTime receivedDateTime) {
        this.poNumber = poNumber;
        this.sellerId = sellerId;
        this.orderStatus = orderStatus;
        this.receivedDateTime = receivedDateTime;
    }

    public PurchaseOrderJpaEntity(String poNumber) {
        this.poNumber = poNumber;
    }
}
