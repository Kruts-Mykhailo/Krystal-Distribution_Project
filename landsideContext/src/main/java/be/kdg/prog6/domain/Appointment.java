package be.kdg.prog6.domain;

public class Appointment {

    private LicensePlate truckLicensePlate;
    private MaterialType materialType;
    private Window arrivalWindow;

    public Appointment(LicensePlate truckLicensePlate, MaterialType materialType, Window arrivalWindow) {
        this.truckLicensePlate = truckLicensePlate;
        this.materialType = materialType;
        this.arrivalWindow = arrivalWindow;
    }

    public LicensePlate getTruckLicensePlate() {
        return truckLicensePlate;
    }

    public void setTruckLicensePlate(LicensePlate truckLicensePlate) {
        this.truckLicensePlate = truckLicensePlate;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public Window getArrivalWindow() {
        return arrivalWindow;
    }

    public void setArrivalWindow(Window arrivalWindow) {
        this.arrivalWindow = arrivalWindow;
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "truckLicensePlate=" + truckLicensePlate +
                ", materialType=" + materialType +
                ", arrivalWindow=" + arrivalWindow +
                '}';
    }
}
