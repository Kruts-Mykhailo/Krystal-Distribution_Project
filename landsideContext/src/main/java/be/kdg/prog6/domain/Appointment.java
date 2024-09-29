package be.kdg.prog6.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Appointment {

    private LicensePlate truckLicensePlate;
    private MaterialType materialType;
    private LocalDateTime appointmentDateTime;
    private UUID warehouseId;
    private int warehouseNumber;

    public Appointment(LicensePlate truckLicensePlate, MaterialType materialType, LocalDateTime appointmentDateTime, UUID warehouseId, int warehouseNumber) {
        this.truckLicensePlate = truckLicensePlate;
        this.materialType = materialType;
        this.appointmentDateTime = appointmentDateTime;
        this.warehouseId = warehouseId;
        this.warehouseNumber = warehouseNumber;
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

    public UUID getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(UUID warehouseId) {
        this.warehouseId = warehouseId;
    }

}
