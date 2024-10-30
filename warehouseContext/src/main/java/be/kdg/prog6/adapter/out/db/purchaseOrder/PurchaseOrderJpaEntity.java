package be.kdg.prog6.adapter.out.db.purchaseOrder;


import be.kdg.prog6.adapter.out.db.orderLine.OrderLineJpaEntity;
import be.kdg.prog6.adapter.out.db.seller.SellerJpaEntity;
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
    private String orderStatus;

    @Column
    private LocalDateTime receivedDateTime;

    @OneToMany(mappedBy = "purchaseOrder")
    private List<OrderLineJpaEntity> orderLines;

    @ManyToOne(fetch = FetchType.LAZY)
    private SellerJpaEntity seller;

    public PurchaseOrderJpaEntity() {
    }

    public PurchaseOrderJpaEntity(String poNumber, String orderStatus, LocalDateTime receivedDateTime) {
        this.poNumber = poNumber;
        this.orderStatus = orderStatus;
        this.receivedDateTime = receivedDateTime;
    }

}
