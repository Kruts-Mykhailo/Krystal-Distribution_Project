package be.kdg.prog6.domain;

public enum UOM {
    KT(1000.0, "kt");

    private final Double measureCoefficient;
    private final String code;

    UOM(Double measureCoefficient, String code) {
        this.measureCoefficient = measureCoefficient;
        this.code = code;
    }

    public Double getMeasureCoefficient() {
        return measureCoefficient;
    }

    public static UOM fromCode(String code) {
        for (UOM uom : UOM.values()) {
            if (uom.code.equals(code)) {
                return uom;
            }
        }
        throw new IllegalArgumentException("Unknown UOM: " + code);
    }


}
