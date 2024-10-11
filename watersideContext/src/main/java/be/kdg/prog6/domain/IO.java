package be.kdg.prog6.domain;

import java.time.LocalDate;

public class IO {
    private String inspectorSignature;
    private LocalDate inspectionDate;
    private InspectionStatus inspectionStatus;

    public IO(String inspectorSignature, LocalDate inspectionDate) {
        this.inspectorSignature = inspectorSignature;
        this.inspectionDate = inspectionDate;
        this.inspectionStatus = inspectorSignature == null || inspectionDate == null ?
                InspectionStatus.OUTSTANDING : InspectionStatus.COMPLETED;

    }

    public String getInspectorSignature() {

        return inspectorSignature;
    }

    public void setInspectorSignature(String inspectorSignature) {
        this.inspectorSignature = inspectorSignature;
    }

    public LocalDate getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(LocalDate inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    public InspectionStatus getInspectionStatus() {
        return inspectionStatus;
    }

    public void setInspectionStatus(InspectionStatus inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }

    public enum InspectionStatus {
        OUTSTANDING, COMPLETED
    }
}
