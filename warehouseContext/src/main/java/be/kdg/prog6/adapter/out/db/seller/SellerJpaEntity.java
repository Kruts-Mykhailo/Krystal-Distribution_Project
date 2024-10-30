package be.kdg.prog6.adapter.out.db.seller;

import be.kdg.prog6.adapter.out.db.warehouse.WarehouseJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
@Setter
@Getter
@Entity
@Table(name = "sellers", catalog = "warehouse")
public class SellerJpaEntity {

    @Id
    private UUID id;

    private String sellerName;

    @OneToMany(mappedBy = "seller")
    private List<WarehouseJpaEntity> warehouses;

    public SellerJpaEntity() {

    }

    public SellerJpaEntity(String sellerName, UUID id) {
        this.sellerName = sellerName;
        this.id = id;
    }

    public SellerJpaEntity(UUID id) {
        this.id = id;
    }
}
