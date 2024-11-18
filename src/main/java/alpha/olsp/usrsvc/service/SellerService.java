package alpha.olsp.usrsvc.service;

import alpha.olsp.usrsvc.model.Seller;

import java.util.Optional;

public interface SellerService {
    Optional<Seller> registerSeller(Seller seller);

    Optional<Seller> findSellerByEmail(String email);
}
