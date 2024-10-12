package be.kdg.prog6.adapters.exceptions;

public class BunkeringOperationDayLimitException extends RuntimeException{
    public BunkeringOperationDayLimitException(String message) {
        super(message);
    }
}
