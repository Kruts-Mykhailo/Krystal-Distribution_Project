package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Warehouse {
    private int warehouseNumber;
    private UUID ownerId;
    private MaterialType materialType;
    private List<PayloadActivityRecord> activityRecords;
    public static Double WAREHOUSE_MAX_CAPACITY = 1_500_000.0;

    public Warehouse(int warehouseNumber, UUID ownerId, List<PayloadActivityRecord> activityRecords) {
        this.warehouseNumber = warehouseNumber;
        this.ownerId = ownerId;
        this.activityRecords = activityRecords;
    }

    public Warehouse(UUID ownerId, int warehouseNumber) {
        this.ownerId = ownerId;
        this.warehouseNumber = warehouseNumber;
        this.activityRecords = new ArrayList<>();
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

    public List<PayloadActivityRecord> getActivityRecords() {
        return activityRecords;
    }

    public void setActivityRecords(List<PayloadActivityRecord> activityRecords) {
        this.activityRecords = activityRecords;
    }

    public MaterialAmount getWarehouseMaterialAmount() {
        return new MaterialAmount(activityRecords.stream().mapToDouble(PayloadActivityRecord::payload).sum(), LocalDateTime.now());
    }

    public PayloadActivityRecord addActivityRecord(LocalDateTime activityTime, Double payloadAmount, ActivityType activityType) {
        PayloadActivityRecord activityRecord = switch (activityType) {
            case DELIVERY -> new PayloadPurchaseEvent(payloadAmount, activityTime);
            case PURCHASE -> new PayloadDeliveryEvent(payloadAmount, activityTime);

        };
        activityRecords.add(activityRecord);
        return activityRecord;
    }
}
