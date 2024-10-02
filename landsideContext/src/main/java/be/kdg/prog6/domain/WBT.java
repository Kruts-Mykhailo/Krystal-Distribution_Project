package be.kdg.prog6.domain;

import java.time.LocalDateTime;

public record WBT(LicensePlate licensePlate,
                  Double weightOnArrival,
                  Double tareWeight,
                  Double netWeight,
                  LocalDateTime arrivalWeighingTime,
                  LocalDateTime departureWeighingTime) {
    @Override
    public String toString() {
        return "WBT{" +
                "licensePlate=" + licensePlate +
                ", weightOnArrival=" + weightOnArrival +
                ", tareWeight=" + tareWeight +
                ", netWeight=" + netWeight +
                ", arrivalWeighingTime=" + arrivalWeighingTime +
                ", departureWeighingTime=" + departureWeighingTime +
                '}';
    }
}
