package be.kdg.prog6.adapter.out.db.payload;


import be.kdg.prog6.adapter.out.db.warehouse.WarehouseJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payloads", catalog = "invoicing")
public class PayloadJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID payloadId;

    @Column(nullable = false)
    private Double tons;

    @Column(nullable = false)
    private LocalDateTime arrivalDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private WarehouseJpaEntity warehouse;

    public PayloadJpaEntity() {

    }

    public PayloadJpaEntity(Double tons, LocalDateTime arrivalDate, WarehouseJpaEntity warehouse) {
        this.tons = tons;
        this.arrivalDate = arrivalDate;
        this.warehouse = warehouse;
    }
}
