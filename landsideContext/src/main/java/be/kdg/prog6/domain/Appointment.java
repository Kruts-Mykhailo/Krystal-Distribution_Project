package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Appointment {

    private UUID id;
    private LicensePlate truckLicensePlate;
    private MaterialType materialType;
    private LocalDateTime appointmentDateTime;
    private UUID warehouseId;
    private int warehouseNumber;
    private AppointmentStatus appointmentStatus;
    private List<AppointmentActivity> appointmentActivities;

    public Appointment(LicensePlate truckLicensePlate, MaterialType materialType, LocalDateTime appointmentDateTime, UUID warehouseId, int warehouseNumber, AppointmentStatus appointmentStatus) {
        this.id = UUID.randomUUID();
        this.truckLicensePlate = truckLicensePlate;
        this.materialType = materialType;
        this.appointmentDateTime = appointmentDateTime;
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.appointmentStatus = appointmentStatus;
        this.appointmentActivities = new ArrayList<>();
    }

    public Appointment(UUID id, LicensePlate truckLicensePlate, MaterialType materialType, LocalDateTime appointmentDateTime, UUID warehouseId, int warehouseNumber, AppointmentStatus appointmentStatus, List<AppointmentActivity> appointmentActivities) {
        this.id = id;
        this.truckLicensePlate = truckLicensePlate;
        this.materialType = materialType;
        this.appointmentDateTime = appointmentDateTime;
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.appointmentStatus = appointmentStatus;
        this.appointmentActivities = appointmentActivities == null ? new ArrayList<>() : appointmentActivities;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public AppointmentStatus getAppointmentStatus() {
        return appointmentStatus;
    }

    public void setAppointmentStatus(AppointmentStatus appointmentStatus) {
        this.appointmentStatus = appointmentStatus;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public LicensePlate getTruckLicensePlate() {
        return truckLicensePlate;
    }

    public void setTruckLicensePlate(LicensePlate truckLicensePlate) {
        this.truckLicensePlate = truckLicensePlate;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public void setMaterialType(MaterialType materialType) {
        this.materialType = materialType;
    }


    public LocalDateTime getAppointmentDateTime() {
        return appointmentDateTime;
    }

    public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
        this.appointmentDateTime = appointmentDateTime;
    }

    public List<AppointmentActivity> getAppointmentActivities() {
        return appointmentActivities;
    }

    public void setAppointmentActivities(List<AppointmentActivity> appointmentActivities) {
        this.appointmentActivities = appointmentActivities;
    }

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }


    private AppointmentStatus getTruckArrivalStatus(LocalDateTime arrivalTime) {
        return arrivalTime.isAfter(this.appointmentDateTime) &&
                arrivalTime.isBefore(this.appointmentDateTime.plusHours(1))
                ? AppointmentStatus.ARRIVED_ON_TIME : AppointmentStatus.ARRIVED_LATE;
    }
    private void addActivity(ActivityType activityType, AppointmentStatus appointmentStatus, LocalDateTime dateTime) {
        AppointmentActivity activity = new AppointmentActivity(
                UUID.randomUUID(),
                this.truckLicensePlate,
                activityType,
                dateTime,
                appointmentStatus
        );
        this.appointmentActivities.add(activity);
    }

    public void truckArrived(LocalDateTime truckArrivalTime) {
        addActivity(ActivityType.ARRIVAL, this.getTruckArrivalStatus(truckArrivalTime), truckArrivalTime);
    }

    public void enterByWeighingBridge(LocalDateTime eventTime) {
        addActivity(ActivityType.PASS_WEIGHING_BRIDGE, AppointmentStatus.ON_SITE, eventTime);
    }

    public void dumpPayload(LocalDateTime eventTime) {
        addActivity(ActivityType.DUMP_LOAD, AppointmentStatus.ON_SITE, eventTime);
    }

    public void leaveByWeighingBridge(LocalDateTime eventTime) {
        addActivity(ActivityType.DEPARTURE, AppointmentStatus.LEFT_SITE, eventTime);
    }




}
