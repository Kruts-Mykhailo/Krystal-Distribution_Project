package be.kdg.prog6.domain;

import java.time.LocalDate;


public class ShipmentOrder {
    private PONumber poReferenceNumber;
    private String customerEnterpriseNumber;
    private String vesselNumber;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private IO inspectionOperation;
    private BO bunkeringOperation;
    private Boolean isMatchedWithPO;
    private ShipmentStatus shipmentStatus;

    public ShipmentOrder(PONumber poReferenceNumber, String customerEnterpriseNumber, String vesselNumber, LocalDate arrivalDate, LocalDate departureDate, IO inspectionOperation, BO bunkeringOperation, Boolean isMatchedWithPO, ShipmentStatus shipmentStatus) {
        this.poReferenceNumber = poReferenceNumber;
        this.customerEnterpriseNumber = customerEnterpriseNumber;
        this.vesselNumber = vesselNumber;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.inspectionOperation = inspectionOperation;
        this.bunkeringOperation = bunkeringOperation;
        this.isMatchedWithPO = isMatchedWithPO;
        this.shipmentStatus = shipmentStatus;
    }

    public void matchPurchaseOrder() {
        this.isMatchedWithPO = true;
    }

    public void leave() {
        this.departureDate = LocalDate.now();
        this.shipmentStatus = ShipmentStatus.LEFT_PORT;
    }

    public boolean canVesselLeave() {
        return this.shipmentStatus == ShipmentStatus.COMPLETED;
    }

    private void updateShipmentStatus() {
        if (inspectionOperation.getInspectionStatus() == IO.InspectionStatus.COMPLETED &&
                bunkeringOperation.getOperationDate() != null &&
                this.isMatchedWithPO) {
            this.shipmentStatus = ShipmentStatus.COMPLETED;
        }
    }

    public void scheduleBO(LocalDate date) {
        this.bunkeringOperation.setOperationDate(date);
        updateShipmentStatus();

    }

    public void completeIO(LocalDate date, String signature) {
        this.inspectionOperation.setInspectionDate(date);
        this.inspectionOperation.setInspectorSignature(signature);
        this.inspectionOperation.setInspectionStatus(IO.InspectionStatus.COMPLETED);
        updateShipmentStatus();
    }

    public ShipmentStatus getShipmentStatus() {
        return shipmentStatus;
    }

    public void setShipmentStatus(ShipmentStatus shipmentStatus) {
        this.shipmentStatus = shipmentStatus;
    }

    public Boolean getMatchedWithPO() {
        return isMatchedWithPO;
    }

    public void setMatchedWithPO(Boolean matchedWithPO) {
        isMatchedWithPO = matchedWithPO;
    }

    public BO getBunkeringOperation() {
        return bunkeringOperation;
    }

    public void setBunkeringOperation(BO bunkeringOperation) {
        this.bunkeringOperation = bunkeringOperation;
    }

    public PONumber getPoReferenceNumber() {
        return poReferenceNumber;
    }

    public void setPoReferenceNumber(PONumber poReferenceNumber) {
        this.poReferenceNumber = poReferenceNumber;
    }


    public String getCustomerEnterpriseNumber() {
        return customerEnterpriseNumber;
    }

    public void setCustomerEnterpriseNumber(String customerEnterpriseNumber) {
        this.customerEnterpriseNumber = customerEnterpriseNumber;
    }

    public String getVesselNumber() {
        return vesselNumber;
    }

    public void setVesselNumber(String vesselNumber) {
        this.vesselNumber = vesselNumber;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public IO getInspectionOperation() {
        return inspectionOperation;
    }

    public void setInspectionOperation(IO inspectionOperation) {
        this.inspectionOperation = inspectionOperation;
    }

    public enum ShipmentStatus {
        OUTSTANDING, COMPLETED, LEFT_PORT
    }

}
