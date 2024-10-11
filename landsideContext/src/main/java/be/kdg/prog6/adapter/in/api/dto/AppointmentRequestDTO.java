package be.kdg.prog6.adapter.in.api.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentRequestDTO {

    private String licensePlate;
    private String materialType;
    private LocalDateTime scheduleDateTime;

    public AppointmentRequestDTO(String licensePlate, String materialType) {
        this.licensePlate = licensePlate;
        this.materialType = materialType;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public LocalDateTime getScheduleDateTime() {
        return scheduleDateTime;
    }

    public void setScheduleDateTime(LocalDateTime scheduleDateTime) {
        this.scheduleDateTime = scheduleDateTime;
    }
}
