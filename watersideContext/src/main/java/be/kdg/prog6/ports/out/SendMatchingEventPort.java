package be.kdg.prog6.ports.out;

import be.kdg.prog6.domain.PONumber;

public interface SendMatchingEventPort {
    void sendMatchingEvent(PONumber poNumber);
}
