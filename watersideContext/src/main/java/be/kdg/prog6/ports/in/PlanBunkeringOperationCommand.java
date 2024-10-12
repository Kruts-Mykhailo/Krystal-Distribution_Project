package be.kdg.prog6.ports.in;

import java.time.LocalDate;

public record PlanBunkeringOperationCommand(String vesselNumber, LocalDate date) {
    public PlanBunkeringOperationCommand {
        if (date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Date must be before now");
        }
    }
}
