package alpha.olsp.usrsvc.service.impl;

import alpha.olsp.usrsvc.model.Admin;
import alpha.olsp.usrsvc.model.Customer;
import alpha.olsp.usrsvc.model.Seller;
import alpha.olsp.usrsvc.model.User;
import alpha.olsp.usrsvc.repository.AdminRepository;
import alpha.olsp.usrsvc.repository.CustomerRepository;
import alpha.olsp.usrsvc.repository.SellerRepository;
import alpha.olsp.usrsvc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final CustomerRepository customerRepository;
    private final SellerRepository sellerRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findUserByEmail(String email) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);
        if (customerOptional.isPresent()) {
            return Optional.of(customerOptional.get());
        }
        Optional<Seller> sellerOptional = sellerRepository.findByEmail(email);
        if (sellerOptional.isPresent()) {
            return Optional.of(sellerOptional.get());
        }
        Optional<Admin> adminOptional = adminRepository.findByEmail(email);
        if (adminOptional.isPresent()) {
            return Optional.of(adminOptional.get());
        }
        return Optional.empty();
    }

    @Override
    public Optional<User> findUserByEmailAndPassword(String email, String password) {
        logger.info("findUserByEmailAndPassword");
        Optional<User> userOptional = findUserByEmail(email);
        if (userOptional.isPresent() && passwordEncoder.matches(password, userOptional.get().getPassword()))
            return userOptional;
        return Optional.empty();
    }

}
