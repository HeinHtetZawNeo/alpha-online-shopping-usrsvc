package alpha.olsp.usrsvc.service.impl;

import alpha.olsp.usrsvc.exception.EmailAlreadyExistsException;
import alpha.olsp.usrsvc.model.Seller;
import alpha.olsp.usrsvc.repository.SellerRepository;
import alpha.olsp.usrsvc.service.SellerService;
import alpha.olsp.usrsvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SellerServiceImpl implements SellerService {

    private final SellerRepository sellerRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<Seller> registerSeller(Seller seller) {
        System.out.println("Inside register seller");
        if (userService.findUserByEmail(seller.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }
        seller.setPassword(passwordEncoder.encode(seller.getPassword()));
        return Optional.of(sellerRepository.save(seller));
    }

    @Override
    public Optional<Seller> findSellerByEmail(String email) {
        return sellerRepository.findByEmail(email);
    }
}
