package be.kdg.prog6.core;

import be.kdg.prog6.port.in.GetTruckAmountOnSiteUseCase;
import be.kdg.prog6.port.out.AppointmentFoundPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class GetTruckAmountOnSiteUseCaseImpl implements GetTruckAmountOnSiteUseCase {

    private final AppointmentFoundPort appointmentFoundPort;

    public GetTruckAmountOnSiteUseCaseImpl(AppointmentFoundPort appointmentFoundPort) {
        this.appointmentFoundPort = appointmentFoundPort;
    }

    @Override
    @Transactional
    public Integer getTruckAmountOnSite() {
        return appointmentFoundPort.getAllAppointmentsOnSite().size();
    }
}
