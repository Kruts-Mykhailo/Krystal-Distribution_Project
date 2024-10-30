package be.kdg.prog6.adapter.out.db.payloadActivity;

import be.kdg.prog6.adapter.out.db.warehouse.WarehouseJpaEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "payload_activities", catalog = "warehouse")
public class PayloadActivityJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String activityType;

    private Double amount;

    private LocalDateTime recordTime;

    @ManyToOne(fetch = FetchType.LAZY)
    private WarehouseJpaEntity warehouse;

    public PayloadActivityJpaEntity(String activityType, Double amount, LocalDateTime recordTime) {
        this.activityType = activityType;
        this.amount = amount;
        this.recordTime = recordTime;
    }

    public PayloadActivityJpaEntity() {

    }
}
