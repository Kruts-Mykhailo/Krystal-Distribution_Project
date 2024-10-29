package be.kdg.prog6.adapter.in.api.dto;

import java.time.LocalDateTime;

public class PdtCopyDTO {
    private String warehouseNumber;
    private LocalDateTime deliveryDateTime;
    private String materialType;
    private int dockNumber;

    public PdtCopyDTO(String warehouseNumber, LocalDateTime deliveryDateTime, String materialType, int dockNumber) {
        this.warehouseNumber = warehouseNumber;
        this.deliveryDateTime = deliveryDateTime;
        this.materialType = materialType;
        this.dockNumber = dockNumber;
    }


    public String getWarehouseNumber() {
        return warehouseNumber;
    }

    public void setWarehouseNumber(String warehouseNumber) {
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
