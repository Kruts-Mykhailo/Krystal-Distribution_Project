package be.kdg.prog6.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class PurchaseOrderCreatedEvent {

    @JsonProperty("purchaseOrder")
    private OrderBody purchaseOrder;

    @Getter
    public static class OrderBody {
        @JsonProperty("poNumber")
        private String poNumber;

        @JsonProperty("referenceUUID")
        private UUID referenceUUID;

        @JsonProperty("customerParty")
        private Party customerParty;

        @JsonProperty("sellerParty")
        private Party sellerParty;

        @JsonProperty("vesselNumber")
        private String vesselNumber;

        @JsonProperty("orderLines")
        private List<PurchaseOrderLine> orderLines;
    }

    @Getter
    public static class Party {
        @JsonProperty("UUID")
        private UUID uuid;

        @JsonProperty("name")
        private String name;

        @JsonProperty("address")
        private String address;
    }

    @Getter
    public static class PurchaseOrderLine {
        @JsonProperty("lineNumber")
        private int lineNumber;

        @JsonProperty("materialType")
        private String materialType;

        @JsonProperty("description")
        private String description;

        @JsonProperty("quantity")
        private int quantity;

        @JsonProperty("uom")
        private String uom;

    }


}
