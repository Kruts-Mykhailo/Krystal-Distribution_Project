package be.kdg.prog6.domain;

public class Truck {
    private LicensePlate licensePlate;
    private MaterialType materialType;

    public Truck(LicensePlate licensePlate, MaterialType materialType) {
        this.licensePlate = licensePlate;
        this.materialType = materialType;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }
}
