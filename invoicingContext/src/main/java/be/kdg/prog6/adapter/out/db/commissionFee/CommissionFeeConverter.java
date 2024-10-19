package be.kdg.prog6.adapter.out.db.commissionFee;

import be.kdg.prog6.domain.CommissionFee;
import be.kdg.prog6.domain.PONumber;
import be.kdg.prog6.domain.Seller;

public class CommissionFeeConverter {

    public static CommissionJpaEntity toJpaEntity(CommissionFee commissionFee) {
        return new CommissionJpaEntity(
                commissionFee.status().name(),
                commissionFee.calculationDateTime(),
                commissionFee.poNumber().number(),
                commissionFee.feeAmount(),
                commissionFee.sellerId().uuid()
        );
    }

    public static CommissionFee fromJpaEntity(CommissionJpaEntity jpaEntity) {
        return new CommissionFee(
                new Seller.SellerId(jpaEntity.getSellerId()),
                jpaEntity.getFeeAmount(),
                new PONumber(jpaEntity.getPoNumber()),
                jpaEntity.getCalculationDate(),
                CommissionFee.FeeStatus.valueOf(jpaEntity.getFeeStatus())
        );
    }
}
