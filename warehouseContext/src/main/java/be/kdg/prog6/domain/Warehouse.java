package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public class Warehouse {
    private WarehouseNumber warehouseNumber;
    private final Seller seller;
    private MaterialType materialType;
    private final List<PayloadActivity> activityRecords;

    public Warehouse(WarehouseNumber warehouseNumber, Seller seller, MaterialType materialType, List<PayloadActivity> activityRecords) {
        this.warehouseNumber = warehouseNumber;
        this.seller = seller;
        this.materialType = materialType;
        this.activityRecords = activityRecords;
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

    public MaterialAmount getWarehouseMaterialAmount() {
        return new MaterialAmount(activityRecords.stream().mapToDouble(PayloadActivity::payload).sum(), LocalDateTime.now());
    }

    public Optional<PayloadActivity> isZeroWeightActivityPresent(LocalDateTime activityDateTime) {
        return  activityRecords.stream()
                        .filter(activity -> activity.getAmount() == 0.0
                                && activity.getEventDateTime().equals(activityDateTime))
                        .findFirst();
    }

}
