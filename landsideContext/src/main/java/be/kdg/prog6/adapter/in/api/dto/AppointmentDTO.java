package be.kdg.prog6.adapter.in.api.dto;

import java.time.LocalDateTime;

public class AppointmentDTO {

    private String licensePlateNumber;
    private String materialType;
    private int warehouseNumber;
    private LocalDateTime windowStartTime;
    private LocalDateTime windowEndTime;

    public AppointmentDTO(String licensePlateNumber, String materialType, int warehouseNumber, LocalDateTime windowStartTime) {
        this.licensePlateNumber = licensePlateNumber;
        this.materialType = materialType;
        this.warehouseNumber = warehouseNumber;
        this.windowStartTime = windowStartTime;
        this.windowEndTime = windowStartTime.plusHours(1);
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public LocalDateTime getWindowStartTime() {
        return windowStartTime;
    }

    public void setWindowStartTime(LocalDateTime windowStartTime) {
        this.windowStartTime = windowStartTime;
    }

    public LocalDateTime getWindowEndTime() {
        return windowEndTime;
    }

    public void setWindowEndTime(LocalDateTime windowEndTime) {
        this.windowEndTime = windowEndTime;
    }
}
