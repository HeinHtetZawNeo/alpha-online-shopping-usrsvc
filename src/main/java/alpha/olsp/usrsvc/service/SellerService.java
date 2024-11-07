package alpha.olsp.usrsvc.service;

import alpha.olsp.usrsvc.model.Seller;
import java.util.Optional;

public interface SellerService {
    public Optional<Seller> registerSeller(Seller seller);
}
