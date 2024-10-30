package be.kdg.prog6.port.out;

import be.kdg.prog6.domain.Seller;

public interface SellerFoundPort {
    Seller getById(Seller.SellerId id);
}
