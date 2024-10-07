package be.kdg.prog6.adapter.out.db.warehouse;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@Entity(name = "warehouse_info")
@Table(name = "warehouses", catalog = "landside")
public class WarehouseInfoJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id")
    private UUID id;

    @Column(name = "warehouse_id", columnDefinition = "BINARY(16)")
    private UUID warehouseId;

    @Column(name = "warehouse_number")
    private int warehouseNumber;

    @Column(name = "seller_id", columnDefinition = "BINARY(16)")
    private UUID sellerId;

    @Column(nullable = false)
    private String materialType;

    @Column(nullable = false)
    private Double initialCapacity;

    @Column(nullable = false)
    private Double maxCapacity;

    public WarehouseInfoJpaEntity() {
    }

    public WarehouseInfoJpaEntity(Double maxCapacity, Double initialCapacity, String materialType, UUID sellerId, int warehouseNumber, UUID warehouseId) {
        this.maxCapacity = maxCapacity;
        this.initialCapacity = initialCapacity;
        this.materialType = materialType;
        this.sellerId = sellerId;
        this.warehouseNumber = warehouseNumber;
        this.warehouseId = warehouseId;
    }
}
