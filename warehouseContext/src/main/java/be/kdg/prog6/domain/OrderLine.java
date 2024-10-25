package be.kdg.prog6.domain;

public record OrderLine(MaterialType materialType, Double quantity, UOM uom) {

    public Double getAmount() {
        return quantity * uom.getMeasureCoefficient();
    }
}
