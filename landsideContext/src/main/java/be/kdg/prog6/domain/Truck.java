package be.kdg.prog6.domain;

public class Truck {
    private LicensePlate licensePlate;
    private MaterialType materialType;
    private PayloadSize payloadSize;

    public Truck(LicensePlate licensePlate, MaterialType materialType) {
        this.licensePlate = licensePlate;
        this.materialType = materialType;
    }

    public PayloadSize getPayloadSize() {
        return payloadSize;
    }

    public void setPayloadSize(PayloadSize payloadSize) {
        this.payloadSize = payloadSize;
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

    public enum PayloadSize {

        SMALL(0.25), BIG(250.0);
        private final double tons;

        PayloadSize(double tons) {
            this.tons = tons;
        }

        public double getTons() {
            return tons;
        }
    }
}
