package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Warehouse {
    private WarehouseNumber warehouseNumber;
    private Seller.SellerId ownerId;
    private MaterialType materialType;
    private List<PayloadActivity> activityRecords;

    public Warehouse(WarehouseNumber warehouseNumber, Seller.SellerId ownerId, MaterialType materialType, List<PayloadActivity> activityRecords) {
        this.warehouseNumber = warehouseNumber;
        this.ownerId = ownerId;
        this.materialType = materialType;
        this.activityRecords = activityRecords;
    }

    public WarehouseNumber getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(WarehouseNumber warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public Seller.SellerId getOwnerId() {
        return ownerId;
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

    public PayloadActivity addActivityRecord(LocalDateTime activityTime, Double payloadAmount, ActivityType activityType) {
        PayloadActivity activityRecord = switch (activityType) {
            case DELIVERY -> new PayloadPurchaseEvent(payloadAmount, activityTime);
            case PURCHASE -> new PayloadDeliveryEvent(payloadAmount, activityTime);

        };
        activityRecords.add(activityRecord);
        return activityRecord;
    }
}
