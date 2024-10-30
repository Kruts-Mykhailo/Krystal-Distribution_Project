package be.kdg.prog6.adapter.out.db.seller;


import be.kdg.prog6.adapter.out.db.appointment.AppointmentJpaEntity;
import be.kdg.prog6.adapter.out.db.warehouse.WarehouseInfoJpaEntity;
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
@Table(name = "sellers", catalog = "landside")
public class SellerJPAEntity {

    @Id
    private UUID sellerId;

    private String sellerName;

    @OneToMany(mappedBy = "seller")
    private List<WarehouseInfoJpaEntity> warehouseInfo;

    @OneToMany(mappedBy = "seller")
    private List<AppointmentJpaEntity> appointments;

    public SellerJPAEntity() {

    }

    public SellerJPAEntity(String sellerName, UUID sellerId) {
        this.sellerName = sellerName;
        this.sellerId = sellerId;
    }

    public SellerJPAEntity(UUID sellerId) {
        this.sellerId = sellerId;
    }
}
