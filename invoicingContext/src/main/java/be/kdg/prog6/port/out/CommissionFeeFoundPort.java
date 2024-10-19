package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.CommissionFee;
import be.kdg.prog6.domain.PONumber;

import java.util.Optional;

public interface CommissionFeeFoundPort {
    Optional<CommissionFee> findByPoNumber(PONumber poNumber);
}
