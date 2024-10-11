package be.kdg.prog6.ports.out;

import be.kdg.prog6.domain.PO;

public interface FindPOPort {

    PO findPOByReferenceNumber(String poNumber);
}
