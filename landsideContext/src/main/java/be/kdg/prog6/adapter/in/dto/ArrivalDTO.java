package be.kdg.prog6.adapter.in.dto;

import be.kdg.prog6.domain.LicensePlate;

import java.time.LocalDateTime;

public class ArrivalDTO {

    private String licensePlate;
    private LocalDateTime arrivalTime;

    public ArrivalDTO(String licensePlate, LocalDateTime arrivalTime) {
        this.licensePlate = licensePlate;
        this.arrivalTime = arrivalTime;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @Override
    public String toString() {
        return "ArrivalDTO{" +
                "licensePlate='" + licensePlate + '\'' +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}
