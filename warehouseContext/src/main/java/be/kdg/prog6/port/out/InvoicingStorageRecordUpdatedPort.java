package be.kdg.prog6.port.out;

import be.kdg.prog6.events.StorageChangeEvent;

public interface InvoicingStorageRecordUpdatedPort {
    void send(StorageChangeEvent event);
}
