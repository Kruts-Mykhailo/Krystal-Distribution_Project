package be.kdg.prog6.adapter.out.db.commissionFee;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "commission_fees", catalog = "invoicing")
public class CommissionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID commissionId;

    @Column(nullable = false)
    private UUID sellerId;

    @Column(nullable = false)
    private Double feeAmount;

    @Column(nullable = false)
    private String poNumber;

    @Column(nullable = false)
    private LocalDateTime calculationDate;

    @Column(nullable = false)
    private String feeStatus;

    public CommissionJpaEntity() {
    }

    public CommissionJpaEntity(String feeStatus, LocalDateTime calculationDate, String poNumber, Double feeAmount, UUID sellerId) {
        this.feeStatus = feeStatus;
        this.calculationDate = calculationDate;
        this.poNumber = poNumber;
        this.feeAmount = feeAmount;
        this.sellerId = sellerId;
    }
}
