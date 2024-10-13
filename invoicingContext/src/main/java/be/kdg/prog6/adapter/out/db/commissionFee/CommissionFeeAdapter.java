package be.kdg.prog6.adapter.out.db.commissionFee;

import be.kdg.prog6.domain.CommissionFee;
import be.kdg.prog6.port.out.SaveCommissionFeePort;
import org.springframework.stereotype.Component;

@Component
public class CommissionFeeAdapter implements SaveCommissionFeePort {
    private final CommissionFeeJpaEntityRepository cfJpaEntityRepository;

    public CommissionFeeAdapter(CommissionFeeJpaEntityRepository cfJpaEntityRepository) {
        this.cfJpaEntityRepository = cfJpaEntityRepository;
    }

    @Override
    public void saveCommissionFee(CommissionFee commissionFee) {
        cfJpaEntityRepository.save(this.convert(commissionFee));
    }
    private CommissionJpaEntity convert(CommissionFee commissionFee) {
        return new CommissionJpaEntity(
                commissionFee.status().name(),
                commissionFee.calculationDateTime(),
                commissionFee.poNumber(),
                commissionFee.feeAmount(),
                commissionFee.sellerId().uuid()
        );
    }
}
