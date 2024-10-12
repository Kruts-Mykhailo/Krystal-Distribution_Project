package be.kdg.prog6.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ShipmentOrder {
    private String poReferenceNumber;
    private List<OrderLine> orderLines;
    private String customerEnterpriseNumber;
    private String vesselNumber;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private IO inspectionOperation;
    private BO bunkeringOperation;
    private Boolean isMatchedWithPO;
    private ShipmentStatus shipmentStatus;

    public ShipmentOrder(String poReferenceNumber, List<OrderLine> orderLines, String customerEnterpriseNumber, String vesselNumber, LocalDate arrivalDate, LocalDate departureDate, IO inspectionOperation, BO bunkeringOperation, Boolean isMatchedWithPO, ShipmentStatus shipmentStatus) {
        this.poReferenceNumber = poReferenceNumber;
        this.orderLines = orderLines;
        this.customerEnterpriseNumber = customerEnterpriseNumber;
        this.vesselNumber = vesselNumber;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.inspectionOperation = inspectionOperation;
        this.bunkeringOperation = bunkeringOperation;
        this.isMatchedWithPO = isMatchedWithPO;
        this.shipmentStatus = shipmentStatus;
    }

    public void matchPurchaseOrder(List<OrderLine> purchaseOrderOrderLines) {
        Map<OrderLine, Long> thisOrderLineCounts = this.orderLines.stream()
                .collect(Collectors.groupingBy(orderLine -> orderLine, Collectors.counting()));

        Map<OrderLine, Long> purchaseOrderLineCounts = purchaseOrderOrderLines.stream()
                .collect(Collectors.groupingBy(orderLine -> orderLine, Collectors.counting()));

        this.isMatchedWithPO = thisOrderLineCounts.equals(purchaseOrderLineCounts);
    }

    public boolean isIOAndBOFulfilled() {
        return inspectionOperation.getInspectionStatus() == IO.InspectionStatus.COMPLETED &&
                bunkeringOperation.getOperationDate() != null;
    }

    public void scheduleBO(LocalDate date) {
        this.bunkeringOperation.setOperationDate(date);
    }

    public void completeIO(LocalDate date, String signature) {
        this.inspectionOperation.setInspectionDate(date);
        this.inspectionOperation.setInspectorSignature(signature);
        this.inspectionOperation.setInspectionStatus(IO.InspectionStatus.COMPLETED);
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

    public String getPoReferenceNumber() {
        return poReferenceNumber;
    }

    public void setPoReferenceNumber(String poReferenceNumber) {
        this.poReferenceNumber = poReferenceNumber;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public void setOrderLines(List<OrderLine> orderLines) {
        this.orderLines = orderLines;
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
        OUTSTANDING, COMPLETED
    }

}
