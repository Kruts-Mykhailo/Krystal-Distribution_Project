package be.kdg.prog6.domain;

public enum MaterialType {
    GYPSUM("GP"), IRON_ORE("IC"), CEMENT("CT"), PETROULEUM_COKE("PT"), SLAG("SK");

    private final String code;
    MaterialType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static MaterialType fromCode(String code) {
        for (MaterialType materialType : MaterialType.values()) {
            if (materialType.getCode().equals(code)) {
                return materialType;
            }
        }
        throw new IllegalArgumentException("Invalid MaterialType: " + code);
    }

}