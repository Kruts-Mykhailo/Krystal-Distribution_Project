package be.kdg.prog6.adapter.out.db.commissionFee;

import be.kdg.prog6.domain.CommissionFee;
import be.kdg.prog6.port.out.CommissionFeeFoundPort;
import be.kdg.prog6.port.out.SaveCommissionFeePort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommissionFeeAdapter implements SaveCommissionFeePort, CommissionFeeFoundPort {
    private final CommissionFeeJpaEntityRepository cfJpaEntityRepository;

    public CommissionFeeAdapter(CommissionFeeJpaEntityRepository cfJpaEntityRepository) {
        this.cfJpaEntityRepository = cfJpaEntityRepository;
    }

    @Override
    public void saveCommissionFee(CommissionFee commissionFee) {
        cfJpaEntityRepository.save(CommissionFeeConverter.toJpaEntity(commissionFee));
    }


    @Override
    public Optional<CommissionFee> findByPoNumber(String poNumber) {
     return cfJpaEntityRepository.findByPoNumber(poNumber).map(CommissionFeeConverter::fromJpaEntity);
    }
}
