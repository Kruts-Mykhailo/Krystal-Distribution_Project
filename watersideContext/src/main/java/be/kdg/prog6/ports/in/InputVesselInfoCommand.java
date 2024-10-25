package be.kdg.prog6.ports.in;


public record InputVesselInfoCommand(String poRefernceNumber, String vesselNumber, String customerEnterpriseNumber) {
    public InputVesselInfoCommand {
        if (vesselNumber.isEmpty()) {
            throw new IllegalArgumentException("Vessel number must not be empty");
        }
        if (customerEnterpriseNumber.isEmpty()) {
            throw new IllegalArgumentException("Customer Enterprise number must not be empty");
        }
        if (poRefernceNumber.isEmpty()) {
            throw new IllegalArgumentException("Purchase order refernce number must not be empty");
        }
        if (!poRefernceNumber.startsWith("PO")) {
            throw new IllegalArgumentException("Purchase order refernce number must start with 'PO'");
        }

    }
}
