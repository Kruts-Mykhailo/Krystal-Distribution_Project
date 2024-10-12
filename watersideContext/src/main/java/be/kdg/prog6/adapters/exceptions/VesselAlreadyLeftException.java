package be.kdg.prog6.adapters.exceptions;

public class VesselAlreadyLeftException extends RuntimeException {
    public VesselAlreadyLeftException(String message) {
        super(message);
    }
}
