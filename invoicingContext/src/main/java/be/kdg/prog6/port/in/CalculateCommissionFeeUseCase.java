package be.kdg.prog6.port.in;

import be.kdg.prog6.events.CommissionEvent;

public interface CalculateCommissionFeeUseCase {
    void calculate(CommissionEvent commissionEvent);
}
