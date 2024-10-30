package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.out.db.seller.SellerJPAEntity;
import be.kdg.prog6.domain.WarehouseNumber;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "warehouses", catalog = "landside")
public class WarehouseInfoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "warehouse_number")
    private String warehouseNumber;

    @Column(name = "seller_id", columnDefinition = "BINARY(16)")
    private UUID sellerId;

    @Column(nullable = false)
    private String materialType;

    @Column(nullable = false)
    private Double initialCapacity;

    @Column(nullable = false)
    private Double maxCapacity;

    @ManyToOne(fetch = FetchType.LAZY)
    private SellerJPAEntity seller;

    public WarehouseInfoJpaEntity() {
    }

    public WarehouseInfoJpaEntity(Double maxCapacity, Double initialCapacity, String materialType, UUID sellerId, String warehouseNumber) {
        this.maxCapacity = maxCapacity;
        this.initialCapacity = initialCapacity;
        this.materialType = materialType;
        this.sellerId = sellerId;
        this.warehouseNumber = warehouseNumber;

    }
}
