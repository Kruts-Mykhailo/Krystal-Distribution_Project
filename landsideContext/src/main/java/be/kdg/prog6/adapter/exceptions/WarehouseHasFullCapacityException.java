package be.kdg.prog6.adapter.exceptions;

public class WarehouseHasFullCapacityException extends RuntimeException{
    public WarehouseHasFullCapacityException(String message) {
        super(message);
    }
}
