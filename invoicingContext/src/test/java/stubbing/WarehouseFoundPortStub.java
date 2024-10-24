package stubbing;

import be.kdg.prog6.domain.MaterialType;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.domain.Warehouse;
import be.kdg.prog6.port.out.WarehouseFoundPort;

import java.time.LocalDateTime;
import java.util.List;

public class WarehouseFoundPortStub implements WarehouseFoundPort {
    @Override
    public Warehouse getBySellerIdAndMaterialType(Seller.SellerId sellerId, MaterialType materialType) {
        Warehouse warehouse = new Warehouse(sellerId, MaterialType.GYPSUM);
        warehouse.addPayload(100.0, LocalDateTime.now());
        warehouse.addPayload(200.0, LocalDateTime.now().plusDays(1));
        return warehouse;
    }

    @Override
    public List<Warehouse> getWarehousesBySellerId(Seller.SellerId sellerId) {

        Warehouse warehouse1 = new Warehouse(sellerId, MaterialType.GYPSUM);
        warehouse1.addPayload(100.0, LocalDateTime.now().minusDays(1));
        warehouse1.addPayload(200.0, LocalDateTime.now().minusDays(2));

        Warehouse warehouse2 = new Warehouse(sellerId, MaterialType.IRON_ORE);
        warehouse2.addPayload(300.0, LocalDateTime.now().minusDays(1));
        warehouse2.addPayload(400.0, LocalDateTime.now().minusDays(2));

        return List.of(warehouse1, warehouse2);
    }
}
