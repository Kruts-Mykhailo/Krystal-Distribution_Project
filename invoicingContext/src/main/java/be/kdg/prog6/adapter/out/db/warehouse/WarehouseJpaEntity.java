package be.kdg.prog6.adapter.out.db.warehouse;


import be.kdg.prog6.adapter.out.db.payload.PayloadJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "warehouses", catalog = "invoicing")
public class WarehouseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID warehouseId;

    @Column(nullable = false)
    private String materialType;

    @Column(nullable = false)
    private UUID sellerId;

    @OneToMany(mappedBy = "warehouse")
    private List<PayloadJpaEntity> payloads;

    public WarehouseJpaEntity() {

    }

    public WarehouseJpaEntity(String materialType, UUID sellerId, List<PayloadJpaEntity> payloads) {
        this.materialType = materialType;
        this.sellerId = sellerId;
        this.payloads = payloads;
    }
}
