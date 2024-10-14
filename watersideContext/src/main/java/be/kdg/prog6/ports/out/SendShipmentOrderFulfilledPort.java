package be.kdg.prog6.ports.out;

public interface SendShipmentOrderFulfilledPort {
    void deductMaterialFromWarehouse(String poNumber);
}
