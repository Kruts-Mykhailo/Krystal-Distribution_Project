package be.kdg.prog6.adapter.out.db.warehouse;

import be.kdg.prog6.adapter.out.db.payloadActivity.PayloadActivityJpaEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "warehouses", catalog = "warehouse")
public class WarehouseJpaEntity {

    @Id
    private String warehouseNumber;

    private String materialType;

    private UUID ownerId;

    @OneToMany(mappedBy = "warehouse")
    private List<PayloadActivityJpaEntity> payloadActivityJpaEntities;


    public WarehouseJpaEntity() {

    }

    public WarehouseJpaEntity(String warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }
}
