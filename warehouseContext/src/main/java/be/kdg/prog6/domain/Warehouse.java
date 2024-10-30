package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class Warehouse {
    private WarehouseNumber warehouseNumber;
    private final Seller seller;
    private MaterialType materialType;
    private final List<PayloadActivity> activityRecords;
    private MaterialAmount currentAmount;

    public Warehouse(WarehouseNumber warehouseNumber, Seller seller, MaterialType materialType, List<PayloadActivity> activityRecords, MaterialAmount currentAmount) {
        this.warehouseNumber = warehouseNumber;
        this.seller = seller;
        this.materialType = materialType;
        this.activityRecords = activityRecords;
        this.currentAmount = currentAmount;
    }

    public Double getMaxCapacity() {
        return 40_000.0;
    }

    public WarehouseNumber getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(WarehouseNumber warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public Seller getSeller() {
        return seller;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public MaterialAmount getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(MaterialAmount currentAmount) {
        this.currentAmount = currentAmount;
    }

    public void snapshot() {
        if (activityRecords.stream().anyMatch(a -> a.getAmount() == 0.0)) {
            throw new IllegalStateException("Activity records contain record with 0 weight. Try again later");
        }
        this.currentAmount = this.getWarehouseMaterialAmount();
    }

    public MaterialAmount getWarehouseMaterialAmount() {
        Double loadedAmountFromActivity = activityRecords.stream().mapToDouble(PayloadActivity::payload).sum();
        return new MaterialAmount(currentAmount.amount() + loadedAmountFromActivity, LocalDateTime.now());
    }

    public Optional<PayloadActivity> isZeroWeightActivityPresent() {
        return  activityRecords.stream()
                        .sorted(Comparator.comparing(PayloadActivity::getEventDateTime).reversed())
                        .filter(activity -> activity.getAmount() == 0.0)
                        .findFirst();
    }

}
