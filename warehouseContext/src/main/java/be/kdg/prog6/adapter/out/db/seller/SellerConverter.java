package be.kdg.prog6.adapter.out.db.seller;

import be.kdg.prog6.domain.Seller;

public class SellerConverter {
    public static Seller fromJpa(SellerJpaEntity sellerJPAEntity) {
        return new Seller(new Seller.SellerId(sellerJPAEntity.getId()), sellerJPAEntity.getSellerName());
    }

    public static SellerJpaEntity toJpa(Seller seller) {
        return new SellerJpaEntity(seller.getName(), seller.getSellerId().id());
    }
}
