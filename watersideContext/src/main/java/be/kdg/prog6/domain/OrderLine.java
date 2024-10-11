package be.kdg.prog6.domain;

import java.util.Objects;

public record OrderLine(MaterialType materialType, Double quantity, UOM uom) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return uom == orderLine.uom && Objects.equals(quantity, orderLine.quantity) && materialType == orderLine.materialType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialType, quantity, uom);
    }
}
