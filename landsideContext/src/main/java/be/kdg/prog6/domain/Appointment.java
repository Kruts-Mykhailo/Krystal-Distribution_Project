package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Appointment {

    private UUID id;
    private final LicensePlate truckLicensePlate;
    private MaterialType materialType;
    private final LocalDateTime scheduledArrivalTime;
    private WarehouseNumber warehouseNumber;
    private TruckArrivalStatus truckArrivalStatus;
    private final List<AppointmentActivity> appointmentActivities;

    public Appointment(LicensePlate truckLicensePlate, MaterialType materialType, LocalDateTime scheduledArrivalTime, WarehouseNumber warehouseNumber, TruckArrivalStatus truckArrivalStatus) {
        this.id = UUID.randomUUID();
        this.truckLicensePlate = truckLicensePlate;
        this.materialType = materialType;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.warehouseNumber = warehouseNumber;
        this.truckArrivalStatus = truckArrivalStatus;
        this.appointmentActivities = new ArrayList<>();
    }

    public Appointment(UUID id, LicensePlate truckLicensePlate, MaterialType materialType, LocalDateTime scheduledArrivalTime, WarehouseNumber warehouseNumber, TruckArrivalStatus truckArrivalStatus, List<AppointmentActivity> appointmentActivities) {
        this.id = id;
        this.truckLicensePlate = truckLicensePlate;
        this.materialType = materialType;
        this.scheduledArrivalTime = scheduledArrivalTime;
        this.warehouseNumber = warehouseNumber;
        this.truckArrivalStatus = truckArrivalStatus;
        this.appointmentActivities = appointmentActivities == null ? new ArrayList<>() : appointmentActivities;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public TruckArrivalStatus getTruckArrivalStatus() {
        return truckArrivalStatus;
    }

    public WarehouseNumber getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(WarehouseNumber warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public LicensePlate getTruckLicensePlate() {
        return truckLicensePlate;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }

    public LocalDateTime getWindowStartTime() {
        return scheduledArrivalTime;
    }

    public LocalDateTime getWindowEndTime() {
        return scheduledArrivalTime.plusHours(1);
    }

    public LocalDateTime getScheduledArrivalTime() {
        return scheduledArrivalTime;
    }

    public List<AppointmentActivity> getAppointmentActivities() {
        return appointmentActivities;
    }

    public void adjustStatusForLateArrival() {
        if (this.getWindowEndTime().isBefore(LocalDateTime.now()) &&
                this.getTruckArrivalStatus() == TruckArrivalStatus.SCHEDULED) {
            this.truckArrivalStatus = TruckArrivalStatus.NOT_ARRIVED_LATE;
        }
    }


    private TruckArrivalStatus updateArrivalStatus(LocalDateTime arrivalTime) {
        return arrivalTime.isAfter(this.scheduledArrivalTime) &&
                arrivalTime.isBefore(this.scheduledArrivalTime.plusHours(1))
                ? TruckArrivalStatus.ON_SITE : TruckArrivalStatus.ARRIVED_LATE;
    }
    private void addActivity(ActivityType activityType, TruckArrivalStatus truckArrivalStatus, LocalDateTime dateTime) {
        AppointmentActivity activity = new AppointmentActivity(
                UUID.randomUUID(),
                this.truckLicensePlate,
                activityType,
                dateTime,
                truckArrivalStatus
        );
        this.appointmentActivities.add(activity);
    }

    public LocalDateTime getDumpPayloadDateTime() {
        return appointmentActivities.stream()
                .filter(activity -> activity.activityType() == ActivityType.DUMP_LOAD)
                .findFirst()
                .map(AppointmentActivity::localDateTime)
                .orElse(null);
    }

    public void truckArrived(LocalDateTime truckArrivalTime) {
        this.truckArrivalStatus = updateArrivalStatus(truckArrivalTime);
        addActivity(ActivityType.ARRIVAL, this.truckArrivalStatus, truckArrivalTime);
    }

    public void enterByWeighingBridge(LocalDateTime eventTime) {
        addActivity(ActivityType.PASS_WEIGHING_BRIDGE, TruckArrivalStatus.ON_SITE, eventTime);
    }

    public void dumpPayload(LocalDateTime eventTime) {
        addActivity(ActivityType.DUMP_LOAD, TruckArrivalStatus.ON_SITE, eventTime);
    }

    public void leaveByWeighingBridge(LocalDateTime eventTime) {
        addActivity(ActivityType.PASS_WEIGHING_BRIDGE, TruckArrivalStatus.LEFT_SITE, eventTime);
    }




}
