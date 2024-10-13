package be.kdg.prog6.domain.materialPricing;

import be.kdg.prog6.domain.MaterialType;

public class MaterialPricingFactory {
    public static MaterialPricing createMaterialPricing(MaterialType materialType) {
        return switch (materialType) {
            case GYPSUM -> new GypsumPricing();
            case IRON_ORE -> new IronOrePricing();
            case CEMENT -> new CementPricing();
            case SLAG -> new SlagPricing();
            case PETROULEUM_COKE -> new PetcokePricing();
        };
    }
}
