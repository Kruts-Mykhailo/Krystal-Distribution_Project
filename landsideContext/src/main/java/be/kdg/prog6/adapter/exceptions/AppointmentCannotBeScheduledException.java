package be.kdg.prog6.adapter.exceptions;

public class AppointmentCannotBeScheduledException extends RuntimeException {
    public AppointmentCannotBeScheduledException(String message) {
        super(message);
    }
}
