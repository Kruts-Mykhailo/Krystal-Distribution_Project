package be.kdg.prog6.adapter.in.dto;

import be.kdg.prog6.domain.LicensePlate;

import java.time.LocalDateTime;

public class ArrivalDTO {

    private LicensePlate licensePlate;
    private LocalDateTime arrivalTime;

    public ArrivalDTO(LicensePlate licensePlate, LocalDateTime arrivalTime) {
        this.licensePlate = licensePlate;
        this.arrivalTime = arrivalTime;
    }

    public LicensePlate getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(LicensePlate licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }
}
