package be.kdg.prog6.ports.out;

import be.kdg.prog6.events.CommissionEvent;

public interface SendCommissionInfoPort {
    void sendInfoForCommission(CommissionEvent commissionEvent);
}
