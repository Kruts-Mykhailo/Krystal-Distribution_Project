package be.kdg.prog6.ports.in;

import java.time.LocalDate;

public interface PlanBunkeringOperationUseCase {

    void planBO(PlanBunkeringOperationCommand command);
}
