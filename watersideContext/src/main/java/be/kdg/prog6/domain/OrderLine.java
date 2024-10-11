package be.kdg.prog6.domain;

import java.util.Objects;

public record OrderLine(MaterialType materialType, Double quantity, UOM uom) {
}
