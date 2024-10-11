package be.kdg.prog6.adapter.in.api.dto;

import be.kdg.prog6.domain.PDT;

import java.time.LocalDateTime;

public class PdtDTO {
    private int warehouseNumber;
    private LocalDateTime deliveryDateTime;
    private String materialType;
    private int dockNumber;

    public PdtDTO(int warehouseNumber, LocalDateTime deliveryDateTime, String materialType, int dockNumber) {
        this.warehouseNumber = warehouseNumber;
        this.deliveryDateTime = deliveryDateTime;
        this.materialType = materialType;
        this.dockNumber = dockNumber;
    }

    public static PdtDTO fromPDT(PDT pdt, int dockNumber) {
        return new PdtDTO(
                pdt.warehouseNumber(),
                pdt.sendTime(),
                pdt.materialType().name(),
                dockNumber
        );
    }

    public int getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(int warehouseNumber) {
        this.warehouseNumber = warehouseNumber;
    }

    public LocalDateTime getDeliveryDateTime() {
        return deliveryDateTime;
    }

    public void setDeliveryDateTime(LocalDateTime deliveryDateTime) {
        this.deliveryDateTime = deliveryDateTime;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }
}
