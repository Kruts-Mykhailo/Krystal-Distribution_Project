package be.kdg.prog6.domain;

import java.time.LocalDateTime;
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
    }

    public Appointment(UUID id, LicensePlate truckLicensePlate, MaterialType materialType, LocalDateTime appointmentDateTime, UUID warehouseId, int warehouseNumber, AppointmentStatus appointmentStatus, List<AppointmentActivity> appointmentActivities) {
        this.id = id;
        this.truckLicensePlate = truckLicensePlate;
        this.materialType = materialType;
        this.appointmentDateTime = appointmentDateTime;
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
        this.appointmentStatus = appointmentStatus;
        this.appointmentActivities = appointmentActivities;
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
                ? AppointmentStatus.ON_TIME : AppointmentStatus.LATE;
    }

    public AppointmentActivity truckArrived(LocalDateTime truckArrivalTime) {
        AppointmentActivity activity = new AppointmentActivity(
                this.truckLicensePlate,
                ActivityType.ARRIVAL,
                truckArrivalTime,
                this.getTruckArrivalStatus(truckArrivalTime)
        );
        this.appointmentActivities.add(activity);
        return activity;
    }
}
