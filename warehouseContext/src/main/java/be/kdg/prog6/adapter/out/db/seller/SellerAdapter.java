package be.kdg.prog6.adapter.out.db.seller;

import be.kdg.prog6.adapter.exceptions.SellerNotFoundException;
import be.kdg.prog6.domain.Seller;
import be.kdg.prog6.port.out.SellerFoundPort;
import org.springframework.stereotype.Component;

@Component
public class SellerAdapter implements SellerFoundPort {

    private final SellerJpaRepository sellerJpaRepository;

    public SellerAdapter(SellerJpaRepository sellerJpaRepository) {
        this.sellerJpaRepository = sellerJpaRepository;
    }

    @Override
    public Seller getById(Seller.SellerId id) {
        return SellerConverter.fromJpa(sellerJpaRepository
                .findById(id.id())
                .orElseThrow(
                        () -> new SellerNotFoundException("Seller with id %s not found".formatted(id.id())))
        );
    }
}
