package be.kdg.prog6.adapter.out.db.seller;

import be.kdg.prog6.domain.Seller;

public class SellerConverter {
    public static Seller fromJpa(SellerJPAEntity sellerJPAEntity) {
        return new Seller(new Seller.SellerId(sellerJPAEntity.getSellerId()), sellerJPAEntity.getSellerName());
    }

    public static SellerJPAEntity toJpa(Seller seller) {
        return new SellerJPAEntity(seller.getName(), seller.getSellerId().id());
    }
}
