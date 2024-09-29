package be.kdg.prog6.adapter.in.dto;

public class AppointmentRequestDTO {

    private String licensePlate;
    private String materialType;

    public AppointmentRequestDTO(String licensePlate, String materialType) {
        this.licensePlate = licensePlate;
        this.materialType = materialType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }
}
