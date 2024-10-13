package be.kdg.prog6.domain;

import be.kdg.prog6.domain.materialPricing.MaterialPricing;
import be.kdg.prog6.domain.materialPricing.MaterialPricingFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class Warehouse {
    private final MaterialType materialType;
    private final List<Payload> payloads;
    private final Seller.SellerId sellerId;
    private final MaterialPricing materialPricing;

    public Warehouse(MaterialType materialType, List<Payload> payloads, Seller.SellerId sellerId, MaterialPricing materialPricing) {
        this.materialType = materialType;
        this.payloads = payloads;
        this.sellerId = sellerId;
        this.materialPricing = materialPricing;
    }

    public Warehouse(Seller.SellerId sellerId, MaterialType materialType, MaterialPricing materialPricing) {
        this.sellerId = sellerId;
        this.materialType = materialType;
        this.materialPricing = materialPricing;
        this.payloads = new ArrayList<>();
    }

    public Double calculateStorageFee() {
        LocalDate calculatedDate = LocalDate.now();
        return payloads
                .stream()
                .mapToDouble(p -> materialPricing.calculateStorageFee(
                        p.getTons(),
                        calculatedDate.getDayOfMonth() - p.getDeliveryDate().getDayOfMonth()))
                .sum();
    }


    public void addPayload(Double tons, LocalDateTime arrivalDate) {
        payloads.add(new Payload(arrivalDate, tons));
    }

    public void removeOldestPayload(Double tonsToRemove) {
        payloads.sort(Comparator.comparing(Payload::getDeliveryDate));
        Iterator<Payload> iterator = payloads.iterator();

        while (iterator.hasNext() && tonsToRemove > 0) {
            Payload payload = iterator.next();
            double currentTons = payload.getTons();

            if (currentTons <= tonsToRemove) {
                tonsToRemove -= currentTons;
                iterator.remove();
            } else {
                payload.setTons(currentTons - tonsToRemove);
                tonsToRemove = 0.0;
            }
        }
    }

    public Double calculateCommissionFee(Double tons) {
        return materialPricing.calculatePricePerTon(tons) * 0.01;
    }

    public List<Payload> getPayloads() {
        return payloads;
    }

    public MaterialType getMaterialType() {
        return materialType;
    }

    public Seller.SellerId getSellerId() {
        return sellerId;
    }
}
