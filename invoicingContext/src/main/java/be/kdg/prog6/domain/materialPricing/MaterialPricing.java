package be.kdg.prog6.domain.materialPricing;

public abstract class MaterialPricing {
    private final double pricePerTon;
    private final double storagePricePerTonPerDay;

    public MaterialPricing(double pricePerTon, double storagePricePerTonPerDay) {
        this.pricePerTon = pricePerTon;
        this.storagePricePerTonPerDay = storagePricePerTonPerDay;
    }

    public double calculateStorageFee(double tonnage, int storageDays) {
        return tonnage * storagePricePerTonPerDay * storageDays;
    }

    public double calculatePricePerTon(double tonnage) {
        return tonnage * pricePerTon;
    }

}