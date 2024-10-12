package be.kdg.prog6.domain;

import java.time.LocalDate;

public class BO {
    private LocalDate operationDate;
    public static int DAY_LIMIT = 6;

    public BO(LocalDate operationDate) {
        this.operationDate = operationDate;
    }

    public BO() {
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }
}
