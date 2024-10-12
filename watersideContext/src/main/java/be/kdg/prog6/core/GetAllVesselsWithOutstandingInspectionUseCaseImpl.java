package be.kdg.prog6.core;

import be.kdg.prog6.domain.ShipmentOrder;
import be.kdg.prog6.ports.in.GetAllOutstandingInspectionOperationsUseCase;
import be.kdg.prog6.ports.out.FindSOPort;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetAllVesselsWithOutstandingInspectionUseCaseImpl implements GetAllOutstandingInspectionOperationsUseCase {

    private final FindSOPort findSOPort;

    public GetAllVesselsWithOutstandingInspectionUseCaseImpl(FindSOPort findSOPort) {
        this.findSOPort = findSOPort;
    }

    @Override
    @Transactional
    public List<ShipmentOrder> getAll() {
        return findSOPort.findAllWithoutIO();
    }
}
