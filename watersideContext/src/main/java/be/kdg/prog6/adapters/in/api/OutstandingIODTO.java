package be.kdg.prog6.adapters.in.api;

public class OutstandingIODTO {
    private String vesselNumber;

    public OutstandingIODTO(String vesselNumber) {
        this.vesselNumber = vesselNumber;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public void setVesselNumber(String vesselNumber) {
        this.vesselNumber = vesselNumber;
    }
}
