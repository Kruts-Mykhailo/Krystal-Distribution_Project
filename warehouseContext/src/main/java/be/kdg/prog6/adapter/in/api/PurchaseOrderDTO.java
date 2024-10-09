package be.kdg.prog6.adapter.in.api;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.PurchaseOrder;
import be.kdg.prog6.domain.Seller;

import java.util.List;
import java.util.UUID;

public class PurchaseOrderDTO {
    private PurchaseOrder.OrderStatus orderStatus;
    private String poNumber;
    private Seller.SellerId sellerId;

    public PurchaseOrderDTO(PurchaseOrder.OrderStatus orderStatus, String poNumber, Seller.SellerId sellerId) {
        this.orderStatus = orderStatus;
        this.poNumber = poNumber;
        this.sellerId = sellerId;
    }

    public PurchaseOrder.OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(PurchaseOrder.OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public Seller.SellerId getSellerId() {
        return sellerId;
    }

    public void setSellerId(Seller.SellerId sellerId) {
        this.sellerId = sellerId;
    }

}
