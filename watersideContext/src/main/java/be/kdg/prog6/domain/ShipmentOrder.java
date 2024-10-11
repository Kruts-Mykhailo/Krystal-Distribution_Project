package be.kdg.prog6.domain;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

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



    public ShipmentOrder(String poReferenceNumber, List<OrderLine> orderLines, String customerEnterpriseNumber, String vesselNumber, LocalDate arrivalDate, LocalDate departureDate) {
        this.poReferenceNumber = poReferenceNumber;
        this.orderLines = orderLines;
        this.customerEnterpriseNumber = customerEnterpriseNumber;
        this.vesselNumber = vesselNumber;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.inspectionOperation = null;
        this.bunkeringOperation = null;
        this.isMatchedWithPO = false;
    }

    public ShipmentOrder(String poReferenceNumber, List<OrderLine> orderLines, String customerEnterpriseNumber, String vesselNumber, LocalDate arrivalDate, LocalDate departureDate, IO inspectionOperation, BO bunkeringOperation, Boolean isMatchedWithPO) {
        this.poReferenceNumber = poReferenceNumber;
        this.orderLines = orderLines;
        this.customerEnterpriseNumber = customerEnterpriseNumber;
        this.vesselNumber = vesselNumber;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.inspectionOperation = inspectionOperation;
        this.bunkeringOperation = bunkeringOperation;
        this.isMatchedWithPO = isMatchedWithPO;
    }

    public void matchPurchaseOrder(List<OrderLine> purchaseOrderOrderLines) {
        this.isMatchedWithPO = new HashSet<>(purchaseOrderOrderLines).containsAll(this.orderLines) &&
                new HashSet<>(this.orderLines).containsAll(purchaseOrderOrderLines);
    }
    public boolean isIOAndBOFulfilled() {
        return inspectionOperation.getInspectionStatus() == IO.InspectionStatus.COMPLETED;
    }

    public void completeBO(LocalDate date) {
        this.bunkeringOperation = new BO(date);
    }

    public void signIO(LocalDate date, String signature) {

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


    public enum Status {
        OUTGOING, COMPLETED
    }

}
