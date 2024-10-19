package be.kdg.prog6.adapters.out.db.shipmentOrder;

import be.kdg.prog6.domain.PONumber;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "shipment_orders", catalog = "waterside")
public class ShipmentOrderJpaEntity {

    @Id
    private PONumber poReferenceNumber;

    @Column(nullable = false)
    private String customerEnterpriseNumber;

    @Column(nullable = false)
    private String vesselNumber;

    @Column(nullable = false)
    private LocalDate arrivalDate;

    @Column
    private LocalDate departureDate;

    @Column
    private LocalDate bunkeringOperationDate;

    @Column
    private LocalDate inspectionOperationDate;

    @Column
    private String inspectorSignature;

    @Column(nullable = false)
    private Boolean isMatchedWithPO;

    @Column(nullable = false)
    private String shipmentStatus;



    public ShipmentOrderJpaEntity() {
    }

    public ShipmentOrderJpaEntity(PONumber poReferenceNumber, String customerEnterpriseNumber, String vesselNumber, LocalDate arrivalDate, LocalDate departureDate, LocalDate bunkeringOperationDate, LocalDate inspectionOperationDate, String inspectorSignature, Boolean isMatchedWithPO, String shipmentStatus) {
        this.poReferenceNumber = poReferenceNumber;
        this.customerEnterpriseNumber = customerEnterpriseNumber;
        this.vesselNumber = vesselNumber;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.bunkeringOperationDate = bunkeringOperationDate;
        this.inspectionOperationDate = inspectionOperationDate;
        this.inspectorSignature = inspectorSignature;
        this.isMatchedWithPO = isMatchedWithPO;
        this.shipmentStatus = shipmentStatus;
    }
}
