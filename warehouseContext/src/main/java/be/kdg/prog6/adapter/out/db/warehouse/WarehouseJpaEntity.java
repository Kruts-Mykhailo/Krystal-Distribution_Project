package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.out.db.payloadActivity.PayloadActivityJpaEntity;
import be.kdg.prog6.adapter.out.db.seller.SellerJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "warehouses", catalog = "warehouse")
public class WarehouseJpaEntity {

    @Id
    private String warehouseNumber;

    private String materialType;
    @OneToMany(mappedBy = "warehouse")
    private List<PayloadActivityJpaEntity> payloadActivityJpaEntities;

    @ManyToOne(fetch = FetchType.LAZY)
    private SellerJpaEntity seller;


    public WarehouseJpaEntity() {

    }

    public WarehouseJpaEntity(String warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }
}
