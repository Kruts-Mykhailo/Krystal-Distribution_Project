package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Warehouse {
    private UUID id;
    private int warehouseNumber;
    private UUID ownerId;
    private MaterialType materialType;
    private List<PayloadActivity> activityRecords;
    public static Double WAREHOUSE_MAX_CAPACITY = 1_500_000.0;

    public Warehouse(UUID id, int warehouseNumber, UUID ownerId, MaterialType materialType, List<PayloadActivity> activityRecords) {
        this.id = id;
        this.warehouseNumber = warehouseNumber;
        this.ownerId = ownerId;
        this.materialType = materialType;
        this.activityRecords = activityRecords;
    }

    public Warehouse(UUID id, MaterialType materialType, UUID ownerId, int warehouseNumber) {
        this.id = id;
        this.materialType = materialType;
        this.ownerId = ownerId;
        this.warehouseNumber = warehouseNumber;
        activityRecords = new ArrayList<>();
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public UUID getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
    }

    public List<PayloadActivity> getActivityRecords() {
        return activityRecords;
    }

    public void setActivityRecords(List<PayloadActivity> activityRecords) {
        this.activityRecords = activityRecords;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isWarehouseAtFullCapacity() {
        return this.getWarehouseMaterialAmount().amount() >= WAREHOUSE_MAX_CAPACITY * 0.80;
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
